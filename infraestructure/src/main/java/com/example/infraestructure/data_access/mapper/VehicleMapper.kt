package com.example.infrastructure.data_access.mapper

import com.example.domain.model.VehicleEntity
import com.example.infraestructure.data_access.models.VehicleModels

fun VehicleEntity.fromModels(): VehicleModels {
    val entity = VehicleModels(plate = this.plate!!, this.typeId, this.cylinder)
    return  entity
}
