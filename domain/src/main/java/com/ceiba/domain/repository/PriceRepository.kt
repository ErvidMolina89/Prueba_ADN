package com.ceiba.domain.repository

import com.ceiba.domain.model.PricesEntity

interface PriceRepository {
    fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesEntity
}
