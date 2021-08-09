package com.ceiba.domain.cost_type_vehicle

import com.ceiba.domain.model.PricesEntity

class ChargeVehicleMotocycleGreater500: ICostVehicle {

    override fun calculatedCostVehicle(
        day:PricesEntity,
        hour:PricesEntity,
        time: MutableList<Int>
    ) : Double {
        return ChargeVehicle().calculatedCostVehicle(day, hour, time) + day.extra!!
    }
}