package com.ceiba.infraestructure.data_access.daos

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.data_access.models.VehicleModels

@Dao
abstract class VehicleDao : BaseDao<VehicleModels> {
    @Query("SELECT * FROM VehicleModels")
    abstract fun getAllVehicleModels(): List<VehicleModels>

    @Query("SELECT * FROM VehicleModels V WHERE V.Plate = :plate")
    abstract fun getVehicleForPlate(plate : String): VehicleModels
}