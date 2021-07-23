package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.CheckModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.ConsultCheckAndVehicle

@Dao
abstract class CheckDao : BaseDao<CheckModels> {
    @Query("SELECT * FROM CheckModels")
    abstract fun getAllCheckModels(): LiveData<List<CheckModels>>
    @Query("SELECT * FROM CheckModels WHERE id = :id")
    abstract fun getCheckModelsId(id:Int): LiveData<CheckModels>
    @Query("SELECT * FROM CheckModels WHERE id = :id")
    abstract fun getConsultCheckAndVehicleId(id:Int): LiveData<ConsultCheckAndVehicle>
}