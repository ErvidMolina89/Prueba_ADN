package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.repository.PriceRepository
import com.example.domain.value_object.PricesValueObj
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromListModels

class PriceRepoRoom : PriceRepository {

    override fun getAllPrices(context: Context): MutableList<PricesValueObj>{
        return DbEstacionamiento.getInstance(context).pricesDao().getAllPricesModels().fromListModels()
    }

}