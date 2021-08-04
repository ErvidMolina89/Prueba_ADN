package com.example.domain.builder

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity


class VehicleAggregateBuilder {
    private var plate = "MAD456"
    private var typeId = 1
    private var cylinder: String? = null
    private var checkEntity = CheckEntity()

    fun withPlate(plate: String): VehicleAggregateBuilder {
        this.plate = plate
        return this
    }

    fun withTypeId(typeId: Int): VehicleAggregateBuilder {
        this.typeId = typeId
        return this
    }

    fun withCylinder(cylinder: String): VehicleAggregateBuilder {
        this.cylinder = cylinder
        return this
    }

    fun withCheckEntity(checkEntity: CheckEntity): VehicleAggregateBuilder {
        this.checkEntity = checkEntity
        return this
    }

    fun buildAggregate(): VehicleAggregate {
        val vehicle = VehicleAggregate()
        vehicle.VehicleAggregate(plate, typeId, cylinder, checkEntity)
        return vehicle
    }

}