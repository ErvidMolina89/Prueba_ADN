package com.example.pruebaadn.view.interfaces

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity

interface CheckViewModelDelegate {
    fun responseEmptyAllCheck()
    fun responseGetAllCheck(list: MutableList<VehicleAggregate>)
    fun responseInsertCheckVehicle()
    fun responseException(message: String?)
    fun responseValidateCosteVehicle(checkEntity: CheckEntity)
}