package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypePriceModels

@Dao
abstract class TypePriceDao : BaseDao<TypePriceModels> {
    @Query("SELECT * FROM TypePriceModels")
    abstract fun getAllTypePriceModels(): LiveData<List<TypePriceModels>>
}