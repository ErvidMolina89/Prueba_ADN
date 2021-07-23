package com.example.pruebaadn.data_source.data_access.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.pruebaadn.data_source.data_access.local_db.entities.VehicleModels

@Dao
abstract class VehicleDao : BaseDao<VehicleModels> {
    @Query("SELECT * FROM VehicleModels")
    abstract fun getAllVehicleModels(): LiveData<List<VehicleModels>>

    @Query("SELECT * FROM VehicleModels WHERE typeID = :id")
    abstract fun getVehicleModelsId(id : Int): LiveData<VehicleModels>

    @Query("SELECT * FROM VehicleModels V WHERE V.Plate = :plate")
    abstract fun getVehicleForPlate(plate : String): LiveData<VehicleModels>
}