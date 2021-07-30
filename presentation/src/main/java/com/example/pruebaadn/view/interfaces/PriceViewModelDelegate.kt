package com.example.pruebaadn.view.interfaces

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.value_object.PricesValueObj

interface PriceViewModelDelegate {
    fun responseAllPrice(list: MutableList<PricesValueObj>)
}