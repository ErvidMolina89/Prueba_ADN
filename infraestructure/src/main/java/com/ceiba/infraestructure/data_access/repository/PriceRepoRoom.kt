package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.PricesEntity
import com.ceiba.domain.repository.PriceRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infraestructure.data_access.daos.PricesDao
import com.ceiba.infrastructure.data_access.mapper.fromValue
import javax.inject.Inject

class PriceRepoRoom @Inject constructor(private val pricesDao: PricesDao) : PriceRepository {

    override fun getPricesForTypeIdAndPriceId(type: Int, typePrice: Int): PricesEntity {
        return pricesDao.getPricesForTypeId(type, typePrice).fromValue()
    }

}