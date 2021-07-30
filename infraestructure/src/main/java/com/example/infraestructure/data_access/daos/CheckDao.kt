package com.example.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.infraestructure.data_access.models.CheckModels
import com.example.infraestructure.data_access.models.ConsultCheckAndVehicle

@Dao
abstract class CheckDao : BaseDao<CheckModels> {
    @Query("SELECT * FROM CheckModels")
    abstract fun getAllCheckModels(): List<CheckModels>
    @Query("SELECT * FROM CheckModels WHERE id = :id")
    abstract fun getCheckModelsId(id:Int): CheckModels
    @Query("SELECT * FROM CheckModels")
    abstract fun getAllConsultCheckAndVehicleId(): List<ConsultCheckAndVehicle>
}