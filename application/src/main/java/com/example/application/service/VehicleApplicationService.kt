package com.example.application.service

import com.example.domain.model.VehicleEntity
import com.example.domain.service.VehicleService

class VehicleApplicationService (private val service: VehicleService) {

    fun insertDataBase(vehicle: VehicleEntity, dateInput: String) : Long {
            return service.insertDataBase(vehicle, dateInput)
    }

}