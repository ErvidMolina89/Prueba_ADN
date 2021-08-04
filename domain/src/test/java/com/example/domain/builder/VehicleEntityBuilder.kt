package com.example.domain.builder

import com.example.domain.model.VehicleEntity


class VehicleEntityBuilder {
    private var plate = "MAD456"
    private var typeId = 1
    private var cylinder: String? = null

    fun withPlate(plate: String): VehicleEntityBuilder {
        this.plate = plate
        return this
    }

    fun buildVehicle(): VehicleEntity {
        val vehicle = VehicleEntity()
        vehicle.VehicleEntity(plate, typeId, cylinder)
        return vehicle
    }

}