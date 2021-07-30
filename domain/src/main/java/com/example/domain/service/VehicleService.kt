package com.example.domain.service

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.VehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.VehicleRepository
import java.util.regex.Matcher
import java.util.regex.Pattern

class VehicleService {
    private lateinit var vehicleRepository: VehicleRepository
    private lateinit var checkRepository: CheckRepository

    fun VehicleService(vehicleRepository: VehicleRepository ){
        this.vehicleRepository = vehicleRepository
    }

    fun CheckService(checkRepository: CheckRepository){
        this.checkRepository = checkRepository
    }

    private fun validatePlateFormat(context: Context,plate: String){
        val pattern: Pattern = Pattern.compile("^[A-Z]{3}[A-Z 0-9]{3}\$")
        val matcher: Matcher = pattern.matcher(plate)
        if (!matcher.matches()){
            throw InvalidDataException("La placa no cuenta con el formato adecuado")
        }
    }

    fun insertVehicleDB(context: Context, vehicle: VehicleAggregate) {
        if (vehicleRepository.vehicleExists(vehicle.plate!!, context)){
            throw InvalidDataException("Vehicle Existe")
        } else {
            validatePlateFormat(context, vehicle.plate!!)
            val entity = VehicleEntity()
            entity.VehicleEntity(vehicle.plate!!, vehicle.typeId!!, vehicle.cylinder)
            val idVehicle = vehicleRepository.insertVehicleDB(context, entity)
            insertCheckDB(idVehicle.toInt(), vehicle.checkEntity, context)
        }
    }

    private fun insertCheckDB(idVehicle: Int, checkEntity: CheckEntity?, context: Context) {
        checkEntity?.vehicleId = idVehicle
        val id = checkRepository.insertCheckDB(context, checkEntity)
        id
    }

}