package com.ceiba.infrastructure.data_access.mapper

import com.ceiba.domain.model.VehicleEntity
import com.ceiba.infraestructure.data_access.models.VehicleModels

fun VehicleEntity.fromModels(): VehicleModels {
    val entity = VehicleModels(plate = this.plate!!, this.typeId, this.cylinder)
    return  entity
}
