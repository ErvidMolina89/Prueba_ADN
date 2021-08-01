package com.example.infrastructure.data_access.mapper

import com.example.domain.entity.DisponibilityEntity
import com.example.infraestructure.data_access.models.variant_init.DisponibilityModels

fun DisponibilityEntity.fromModels(): DisponibilityModels {
    val entity = DisponibilityModels()
    entity.id = this.id
    entity.typeId = this.typeId
    entity.count = this.count
    return  entity
}

fun DisponibilityModels.fromEntity(): DisponibilityEntity {
    val entity = DisponibilityEntity()
    entity.id = this.id
    entity.typeId = this.typeId
    entity.count = this.count
    return  entity
}

fun List<DisponibilityModels>.fromListEntity(): MutableList<DisponibilityEntity>{
    val listEntity = emptyList<DisponibilityEntity>().toMutableList()
        this.forEach { models ->
            val item  = DisponibilityEntity()
            item.DisponibilityEntity(models.id!!, models.typeId!!, models.count!!)
            listEntity.add(item)
        }
    return listEntity
}