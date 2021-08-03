package com.example.infrastructure.data_access.mapper

import com.example.domain.model.PricesEntity
import com.example.infraestructure.data_access.models.variant_init.PricesModels


fun PricesModels.fromValue(): PricesEntity {
        val item  = PricesEntity()
        item.typeId = this.typeId
        item.value = this.value
        item.extra = this.extra
        item.typePrice = this.typePrice
    return item
}