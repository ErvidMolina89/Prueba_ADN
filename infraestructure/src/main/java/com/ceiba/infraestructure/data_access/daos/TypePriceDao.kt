package com.ceiba.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.data_access.models.variant_init.TypePriceModels

@Dao
abstract class TypePriceDao : BaseDao<TypePriceModels> {
    @Query("SELECT * FROM TypePriceModels")
    abstract fun getAllTypePriceModels(): List<TypePriceModels>
}