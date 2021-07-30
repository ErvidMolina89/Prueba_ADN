package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.entity.VehicleEntity
import com.example.domain.repository.VehicleRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromModels

class VehicleRepoRoom : VehicleRepository {

    override fun vehicleExists(id: String, context: Context): Boolean {
        val vehicle = DbEstacionamiento.getInstance(context).vehicleDao().getVehicleForPlate(id)
        if (vehicle != null) return true
        return false
    }

    override fun insertVehicleDB(context: Context, vehicleEntity: VehicleEntity): Long {
        return DbEstacionamiento.getInstance(context).vehicleDao().insert(vehicleEntity.fromModels())
    }

}