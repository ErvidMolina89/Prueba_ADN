package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.model.PricesEntity
import com.example.domain.repository.PriceRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromValue

class PriceRepoRoom (private val context: Context) : PriceRepository {

    override fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesEntity {
        return DbEstacionamiento.getInstance(context).pricesDao().getPricesForTypeId(type, typePrice).fromValue()
    }

}