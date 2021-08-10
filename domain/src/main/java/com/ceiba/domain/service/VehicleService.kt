package com.ceiba.domain.service

import com.ceiba.domain.R
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.DisponibilityEntity
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.domain.repository.CheckRepository
import com.ceiba.domain.repository.DisponibilityRepository
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.domain.util.convertToFormatDate
import java.util.*
import javax.inject.Inject

class VehicleService @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val checkRepository: CheckRepository,
    private val disponibilityRepository: DisponibilityRepository
        ) {

    private lateinit var disponibility: DisponibilityEntity
    private val WhenAvailabilityIsZero = 0
    private val ResponseWhenVehicleExistsButHasNoInvoice: Long = 0
    private val DecreaseInDisponibility = 1

    fun insertDataBase(vehicle: VehicleEntity, dateInput: String) : Long {
        disponibility = DisponibilityEntity()
        if (vehicleRepository.vehicleExists(vehicle.plate!!)) {
            return if (checkRepository.getModelsForPlateId(vehicle.plate!!)){
                throw InvalidDataException(R.string.vehicle_exists.toString())
            }else ResponseWhenVehicleExistsButHasNoInvoice
        }
        validationsRelatedToVehicleCreation(vehicle, dateInput)
        return vehicleRepository.insertDataBase(vehicle)
    }

    private fun validationsRelatedToVehicleCreation(vehicle: VehicleEntity, dateInput: String){
        if (!validateEntryDateVehicle(vehicle.plate!!, dateInput)){
            throw InvalidDataException(R.string.autorize_not_parking.toString())
        }
        if (validateDisponibilityVehicle(vehicle.typeId!!)){
            throw InvalidDataException(R.string.parking_not_available.toString())
        }
    }

    private fun validateEntryDateVehicle(plate: String, dateInput: String): Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = dateInput.convertToFormatDate()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        val subString = plate.substring(0, 1)
        if (subString == "A" && (day != Calendar.SUNDAY && day != Calendar.MONDAY)) {
            return false
        }
        return true
    }

    private fun validateDisponibilityVehicle (type: Int): Boolean {
        disponibility = disponibilityRepository.getDisponibilityForTypeId(type)
        if(disponibility.count!! <= WhenAvailabilityIsZero){
            return true
        }
        updateDisponibility(disponibility)
        return false
    }

    private fun updateDisponibility(disponibility: DisponibilityEntity) {
        disponibility.count = disponibility.count!! - DecreaseInDisponibility
        disponibilityRepository.update(disponibility)
    }

}