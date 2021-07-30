package com.example.infrastructure.data_access.mapper

import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.TypeVehicleEntity
import com.example.infraestructure.data_access.models.variant_init.DisponibilityModels
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

fun DisponibilityEntity.fromModels(): DisponibilityModels {
    val entity = DisponibilityModels().apply {
        id = this.id
        typeId = this.typeId
        count = this.count
    }
    return  entity
}

fun List<DisponibilityModels>.fromListModels(): MutableList<DisponibilityEntity>{
    val listEntity = emptyList<DisponibilityEntity>().toMutableList()
        this.forEach { models ->
            val item  = DisponibilityEntity()
            item.DisponibilityEntity(models.id!!, models.typeId!!, models.count!!)
            listEntity.add(item)
        }
    return listEntity
}