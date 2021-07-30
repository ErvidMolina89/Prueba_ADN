package com.example.pruebaadn.view.interfaces

import com.example.domain.aggregate.VehicleAggregate

interface CheckViewModelDelegate {
    fun responseEmptyAllCheck()
    fun responseGetAllCheck(list: MutableList<VehicleAggregate>)
}