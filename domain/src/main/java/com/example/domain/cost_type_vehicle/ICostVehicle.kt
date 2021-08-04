package com.example.domain.cost_type_vehicle

import com.example.domain.model.PricesEntity

interface ICostVehicle {
    fun calculatedCostVehicle(day:PricesEntity, hour:PricesEntity, time: MutableList<Int>): Double
}