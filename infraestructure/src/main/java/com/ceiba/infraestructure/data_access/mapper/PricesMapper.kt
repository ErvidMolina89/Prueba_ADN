package com.ceiba.infrastructure.data_access.mapper

import com.ceiba.domain.model.PricesEntity
import com.ceiba.infraestructure.data_access.models.variant_init.PricesModels


fun PricesModels.fromValue(): PricesEntity {
        val item  = PricesEntity()
        item.typeId = this.typeId
        item.value = this.value
        item.extra = this.extra
        item.typePrice = this.typePrice
    return item
}