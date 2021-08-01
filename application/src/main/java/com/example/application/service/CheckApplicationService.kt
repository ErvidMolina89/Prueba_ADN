package com.example.application.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.service.CheckService

class CheckApplicationService (private val service: CheckService) {

    fun getAllCheck(): MutableList<VehicleAggregate>{
        return service.getAllCheck()
    }

    fun insertChechVehicle(checkEntity: CheckEntity): Long {
        return service.insertChechVehicle(checkEntity)
    }

    fun validateCosteVehicle(checkAggregate: VehicleAggregate): CheckEntity {
        return service.validateCosteVehicle(checkAggregate)
    }
}