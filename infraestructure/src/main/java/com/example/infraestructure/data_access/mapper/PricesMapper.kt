package com.example.infrastructure.data_access.mapper

import com.example.domain.value_object.PricesValueObj
import com.example.infraestructure.data_access.models.variant_init.PricesModels


fun PricesModels.fromValue(): PricesValueObj{
        val item  = PricesValueObj()
        item.typeId = this.typeId
        item.value = this.value
        item.extra = this.extra
        item.typePrice = this.typePrice
    return item
}