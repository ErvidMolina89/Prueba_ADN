package com.example.domain.cost_type_vehicle

import com.example.domain.model.PricesEntity
import com.example.domain.util.MinimunTimeCostEnum

class ChargeVehicle: ICostVehicle {

    override fun calculatedCostVehicle(
        day:PricesEntity,
        hour:PricesEntity,
        time: MutableList<Int>
    ) : Double {
        if (validateDataValueZero(time)){
            return hour.value!!
        }
        return costHoursVehicle(time, hour) + costDayVehicle(time, day)
    }

    private fun validateDataValueZero(
        time: MutableList<Int>
    ): Boolean{
        if (time.first() == MinimunTimeCostEnum.VALUEZERO.getTags() && time.last() == MinimunTimeCostEnum.VALUEZERO.getTags()){
            return true
        }
        return false
    }

    private fun costHoursVehicle(
        time: MutableList<Int>,
        hour:PricesEntity
    ): Double{
        return time.last() * hour.value!!
    }

    private fun costDayVehicle(
        time: MutableList<Int>,
        day:PricesEntity
    ): Double{
        return time.first() * day.value!!
    }
}