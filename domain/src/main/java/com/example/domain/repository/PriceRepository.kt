package com.example.domain.repository

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.value_object.PricesValueObj

interface PriceRepository {

    fun getAllPrices(context: Context): MutableList<PricesValueObj>

}
