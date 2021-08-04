package com.example.domain.builder

import com.example.domain.model.PricesEntity


class PriceEntityBuilder {
    private var typeId = 1
    private var typePrice = 1
    private var value = 8000.0
    private var extra = 2000.0

    fun withTypeId(typeId: Int): PriceEntityBuilder {
        this.typeId = typeId
        return this
    }

    fun withTypePrice(typePrice: Int): PriceEntityBuilder {
        this.typePrice = typePrice
        return this
    }

    fun withValue(value: Double): PriceEntityBuilder {
        this.value = value
        return this
    }

    fun withExtra(extra: Double): PriceEntityBuilder {
        this.extra = extra
        return this
    }

    fun buildPrice(): PricesEntity {
        val price = PricesEntity()
        price.PricesEntity(typeId, typePrice, value, extra)
        return price
    }

}