package com.example.infrastructure.data_access.mapper

import com.example.domain.entity.VehicleEntity
import com.example.infraestructure.data_access.models.VehicleModels
import com.example.infraestructure.data_access.models.variant_init.DisponibilityModels
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

fun VehicleEntity.fromModels(): VehicleModels {
    val entity = VehicleModels(plate = this.plate!!, this.typeId, this.cylinder)
    return  entity
}
