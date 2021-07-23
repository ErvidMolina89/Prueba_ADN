package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.DetailsVehicleModels

@Dao
abstract class DetailsVehicleDao : BaseDao<DetailsVehicleModels> {
    @Query("SELECT * FROM DetailsVehicleModels")
    abstract fun getAllDetailsVehicleModels(): LiveData<List<DetailsVehicleModels>>
    @Query("SELECT * FROM DetailsVehicleModels DVM WHERE DVM.id = :id")
    abstract fun getDetailsVehicleModelsForId(id:Int): LiveData<DetailsVehicleModels>
    @Query("SELECT * FROM DetailsVehicleModels DVM WHERE DVM.VehicleId   = :id")
    abstract fun getDetailVehicleForVehicleId(id: Int): LiveData<DetailsVehicleModels>
}