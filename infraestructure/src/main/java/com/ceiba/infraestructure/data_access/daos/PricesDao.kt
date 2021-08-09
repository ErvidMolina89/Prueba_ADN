package com.ceiba.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.data_access.models.variant_init.PricesModels

@Dao
abstract class PricesDao : BaseDao<PricesModels> {
    @Query("SELECT * FROM PricesModels")
    abstract fun getAllPricesModels(): List<PricesModels>

    @Query("SELECT * FROM PricesModels WHERE TypeId = :type ANd TypePrice = :typePrice")
    abstract fun getPricesForTypeId(type : Int, typePrice: Int): PricesModels
}