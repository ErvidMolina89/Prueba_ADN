package com.ceiba.application.service

import com.ceiba.domain.model.VehicleEntity
import com.ceiba.domain.service.VehicleService
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private val service: VehicleService) {

    fun insertDataBase(vehicle: VehicleEntity, dateInput: String) : Long {
            return service.insertDataBase(vehicle, dateInput)
    }

}