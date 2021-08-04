package com.example.domain.cost_type_vehicle

import com.example.domain.model.PricesEntity
import com.example.domain.util.MinimunTimeCostEnum

class ChargeVehicleMotocycleGreater500: ICostVehicle {

    override fun calculatedCostVehicle(
        day:PricesEntity,
        hour:PricesEntity,
        time: MutableList<Int>
    ) : Double {
        return ChargeVehicle().calculatedCostVehicle(day, hour, time) + day.extra!!
    }
}