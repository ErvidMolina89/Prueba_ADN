package com.ceiba.infrastructure.data_access.mapper

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity
import com.ceiba.infraestructure.data_access.models.CheckModels
import com.ceiba.infraestructure.data_access.models.ConsultCheckAndVehicle

fun CheckEntity.fromModels(): CheckModels {
    val model = CheckModels()
    model.id = this.id
    model.plateId = this.plateId
    model.dateInput = this.dateInput
    model.dateExit  = this.dateExit
    model.totalCost = this.totalCost

    return  model
}

fun CheckModels.fromEntity(): CheckEntity {
    val entity = CheckEntity()
    entity.id = this.id
    entity.plateId = this.plateId
    entity.dateInput = this.dateInput
    entity.dateExit  = this.dateExit
    entity.totalCost = this.totalCost

    return  entity
}

fun List<ConsultCheckAndVehicle>.fromMutableAggregate():  MutableList<VehicleAggregate> {
    val list = emptyList<VehicleAggregate>().toMutableList()
    this.forEach {
        val aggregate = VehicleAggregate()
        aggregate.plate = it.vehicleModels?.plate
        aggregate.typeId = it.vehicleModels?.typeId
        aggregate.cylinder = it.vehicleModels?.cylinder
        aggregate.checkEntity = it.checkModels?.fromEntity()
        if (aggregate.checkEntity?.totalCost == null) {
            list.add(aggregate)
        }
    }
    return list
}
