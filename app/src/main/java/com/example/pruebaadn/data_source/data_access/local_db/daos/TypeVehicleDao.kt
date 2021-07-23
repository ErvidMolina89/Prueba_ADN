package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels

@Dao
abstract class TypeVehicleDao : BaseDao<TypeVehicleModels> {
    @Query("SELECT * FROM TypeVehicleModels")
    abstract fun getAllTypeVehicleModels(): LiveData<List<TypeVehicleModels>>
}