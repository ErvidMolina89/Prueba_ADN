package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.PricesEntity
import com.ceiba.domain.repository.PriceRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infrastructure.data_access.mapper.fromValue

class PriceRepoRoom (private val context: Context) : PriceRepository {

    override fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesEntity {
        return DbEstacionamiento.getInstance(context).pricesDao().getPricesForTypeId(type, typePrice).fromValue()
    }

}