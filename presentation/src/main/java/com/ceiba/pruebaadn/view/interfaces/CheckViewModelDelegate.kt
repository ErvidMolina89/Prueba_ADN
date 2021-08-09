package com.ceiba.pruebaadn.view.interfaces

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity

interface CheckViewModelDelegate {
    fun responseEmptyAllCheck()
    fun responseGetAllCheck(list: MutableList<VehicleAggregate>)
    fun responseInsertCheckVehicle()
    fun responseException(message: String?)
    fun responseValidateCosteVehicle(checkEntity: CheckEntity)
}