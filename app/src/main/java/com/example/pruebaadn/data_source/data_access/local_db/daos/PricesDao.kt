package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.PricesModels

@Dao
abstract class PricesDao : BaseDao<PricesModels> {
    @Query("SELECT * FROM PricesModels")
    abstract fun getAllPricesModels(): LiveData<List<PricesModels>>

    @Query("SELECT * FROM PricesModels PM WHERE PM.TypeId = :type")
    abstract fun getPricesForTypeId(type : Int): LiveData<List<PricesModels>>
}