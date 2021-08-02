package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.VehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.VehicleRepository
import com.example.domain.util.convertToFormatDate
import java.util.*

class VehicleService (
    private val vehicleRepository: VehicleRepository,
    private val disponibilityRepository: DisponibilityRepository
        ) {

    private lateinit var disponibility: DisponibilityEntity

    fun insertVehicleDB(vehicle: VehicleEntity, dateInput: String) : Long {
        disponibility = DisponibilityEntity()
        if (vehicleRepository.vehicleExists(vehicle.plate!!)) {
            return throw InvalidDataException("Vehicle Exist")
        }
        try {
            validationsRelatedToVehicleCreation(vehicle, dateInput)
        }catch (e: InvalidDataException){
            return throw InvalidDataException(e.message)
        }
        return vehicleRepository.insertVehicleDB(vehicle)
    }

    private fun validationsRelatedToVehicleCreation(vehicle: VehicleEntity, dateInput: String){
        if (!validateEntryDateVehicle(vehicle.plate!!, dateInput)){
            return throw InvalidDataException("Vehicle Parking Not Autorize why his Plate Init A")
        }
        if (validateDisponibilityVehicle(vehicle.typeId!!)){
            return throw InvalidDataException("Parking Not Disponibility")
        }
    }

    private fun validateEntryDateVehicle(plate: String, dateInput: String): Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = dateInput.convertToFormatDate()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val subString = plate.substring(0, 1)
        if (subString == "A" && (day == Calendar.SUNDAY || day == Calendar.MONDAY)) {
            return true
        }
        return false
    }

    private fun validateDisponibilityVehicle (type: Int): Boolean {
        disponibility = disponibilityRepository.getDisponibilityForTypeId(type)
        if(disponibility.count!! <= 0){
            return true
        }
        updateDisponibility(disponibility)
        return false
    }

    private fun updateDisponibility(disponibility: DisponibilityEntity) {
        disponibility.count = disponibility.count!! - 1
        disponibilityRepository.updateDisponibility(disponibility)
    }

}