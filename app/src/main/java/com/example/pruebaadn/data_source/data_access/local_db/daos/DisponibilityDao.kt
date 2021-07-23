package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels

@Dao
abstract class DisponibilityDao : BaseDao<DisponibilityModels> {
    @Query("SELECT * FROM DisponibilityModels")
    abstract fun getAllDisponibilityModels(): LiveData<List<DisponibilityModels>>

    @Query("SELECT * FROM DisponibilityModels DM WHERE DM.TypeId = :id")
    abstract fun getDisponibilityModelsTypeId(id:Int): LiveData<DisponibilityModels>
}