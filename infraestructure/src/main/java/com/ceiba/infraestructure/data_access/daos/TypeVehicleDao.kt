package com.ceiba.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.data_access.models.variant_init.TypeVehicleModels

@Dao
abstract class TypeVehicleDao : BaseDao<TypeVehicleModels> {
    @Query("SELECT * FROM TypeVehicleModels")
    abstract fun getAllTypeVehicleModels(): List<TypeVehicleModels>
}