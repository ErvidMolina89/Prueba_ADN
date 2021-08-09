package com.ceiba.application.service

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.service.CheckService

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