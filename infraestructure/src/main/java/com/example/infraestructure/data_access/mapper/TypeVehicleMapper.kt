package com.example.infrastructure.data_access.mapper

import com.example.domain.entity.TypeVehicleEntity
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

fun TypeVehicleModels.fromEntity(typeVehicleEntity: TypeVehicleEntity): TypeVehicleModels {
    return TypeVehicleModels().apply {
        id = typeVehicleEntity.id
        name = typeVehicleEntity.name
    }
}

fun TypeVehicleEntity.fromModels(typeVehicleModels: TypeVehicleModels): TypeVehicleEntity {
    return  TypeVehicleEntity().apply {
        id = typeVehicleModels.id
        name = typeVehicleModels.name
    }
}

fun List<TypeVehicleModels>.fromListModels(): MutableList<TypeVehicleEntity>{
    val listEntity = emptyList<TypeVehicleEntity>().toMutableList()
        this.forEach { models ->
            val item  = TypeVehicleEntity()
            item.TypeVehicleEntity(models.id!!, models.name!!)
            listEntity.add(item)
        }
    return listEntity
}