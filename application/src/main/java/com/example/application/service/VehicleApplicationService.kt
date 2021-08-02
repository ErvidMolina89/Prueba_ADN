package com.example.application.service

import com.example.domain.entity.VehicleEntity
import com.example.domain.service.VehicleService

class VehicleApplicationService (private val service: VehicleService) {

    fun insertVehicleDB(vehicle: VehicleEntity, dateInput: String) : Long {
        return service.insertVehicleDB(vehicle, dateInput)
    }

}