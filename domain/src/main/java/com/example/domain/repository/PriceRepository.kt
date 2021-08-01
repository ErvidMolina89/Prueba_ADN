package com.example.domain.repository

import com.example.domain.value_object.PricesValueObj

interface PriceRepository {
    fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesValueObj
}
