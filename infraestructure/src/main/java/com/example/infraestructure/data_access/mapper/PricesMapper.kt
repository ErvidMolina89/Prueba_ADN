package com.example.infrastructure.data_access.mapper

import com.example.domain.value_object.PricesValueObj
import com.example.infraestructure.data_access.models.variant_init.PricesModels


fun List<PricesModels>.fromListModels(): MutableList<PricesValueObj>{
    val listEntity = emptyList<PricesValueObj>().toMutableList()
        this.forEach { models ->
            val item  = PricesValueObj()
            item.typeId = models.typeId
            item.value = models.value
            item.extra = models.extra
            item.typePrice = models.typePrice
            listEntity.add(item)
        }
    return listEntity
}