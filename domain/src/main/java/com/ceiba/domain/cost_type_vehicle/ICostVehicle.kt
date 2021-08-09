package com.ceiba.domain.cost_type_vehicle

import com.ceiba.domain.model.PricesEntity

interface ICostVehicle {
    fun calculatedCostVehicle(day:PricesEntity, hour:PricesEntity, time: MutableList<Int>): Double
}