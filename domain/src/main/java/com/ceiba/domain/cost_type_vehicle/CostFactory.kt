package com.ceiba.domain.cost_type_vehicle

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.util.TypeVehicleEnum

class CostFactory {
    private val motorcycleCylinderGreaterThan500 = 500

    fun getCost(
        check: VehicleAggregate
    ): ICostVehicle {
        return when(check.typeId){
            TypeVehicleEnum.CAR.getTags() -> {ChargeVehicle()}
            TypeVehicleEnum.MOTOCYCLE.getTags() -> {
                if (check.cylinder!!.toInt() >= motorcycleCylinderGreaterThan500){
                    ChargeVehicleMotocycleGreater500()
                } else ChargeVehicle()
            }
            else -> {ChargeVehicle()}
        }
    }
}