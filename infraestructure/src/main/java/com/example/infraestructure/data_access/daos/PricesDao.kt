package com.example.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.infraestructure.data_access.models.variant_init.PricesModels

@Dao
abstract class PricesDao : BaseDao<PricesModels> {
    @Query("SELECT * FROM PricesModels")
    abstract fun getAllPricesModels(): List<PricesModels>

    @Query("SELECT * FROM PricesModels PM WHERE PM.TypeId = :type")
    abstract fun getPricesForTypeId(type : Int): List<PricesModels>
}