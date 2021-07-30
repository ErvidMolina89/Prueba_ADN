package com.example.infrastructure.data_access.mapper

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.infraestructure.data_access.models.CheckModels
import com.example.infraestructure.data_access.models.ConsultCheckAndVehicle

fun CheckEntity.fromModels(): CheckModels {
    val entity = CheckModels().apply {
        id = this.id
        vehicleId = this.vehicleId
        dateInput = this.dateInput
        dateExit  = this.dateExit
        totalCost = this.totalCost
    }
    return  entity
}

fun CheckModels.fromEntity(): CheckEntity {
    val entity = CheckEntity().apply {
        id = this.id
        vehicleId = this.vehicleId
        dateInput = this.dateInput
        dateExit  = this.dateExit
        totalCost = this.totalCost
    }
    return  entity
}

fun List<ConsultCheckAndVehicle>.fromMutableAggregate():  MutableList<VehicleAggregate> {
    val list = emptyList<VehicleAggregate>().toMutableList()
    this.map {
        val aggregate = VehicleAggregate()
        aggregate.plate = it.vehicleModels?.plate
        aggregate.typeId = it.vehicleModels?.typeId
        aggregate.cylinder = it.vehicleModels?.cylinder
        aggregate.checkEntity = it.checkModels?.fromEntity()
    }
    return list
}
