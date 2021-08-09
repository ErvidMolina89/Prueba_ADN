package com.ceiba.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.data_access.models.variant_init.DisponibilityModels

@Dao
abstract class DisponibilityDao : BaseDao<DisponibilityModels> {
    @Query("SELECT * FROM DisponibilityModels")
    abstract fun getAllDisponibilityModels(): List<DisponibilityModels>

    @Query("SELECT * FROM DisponibilityModels DM WHERE DM.TypeId = :id")
    abstract fun getDisponibilityModelsTypeId(id:Int): DisponibilityModels
}