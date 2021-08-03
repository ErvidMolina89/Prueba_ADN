package com.example.application.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity
import com.example.domain.service.CheckService

class CheckApplicationService (private val service: CheckService) {

    fun getAllC(): MutableList<VehicleAggregate>{
        return service.getAllCheck()
    }

    fun insertInvoice(checkEntity: CheckEntity): Long {
        return service.insertCheckVehicle(checkEntity)
    }

    fun validateCosteInvoiceVehicle(checkAggregate: VehicleAggregate): CheckEntity {
        return service.validateCostVehicle(checkAggregate)
    }
}