package com.example.domain.repository

import com.example.domain.model.PricesEntity

interface PriceRepository {
    fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesEntity
}
