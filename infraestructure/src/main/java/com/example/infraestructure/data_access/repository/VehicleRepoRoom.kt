package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.model.VehicleEntity
import com.example.domain.repository.VehicleRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromModels

class VehicleRepoRoom  (private val context: Context) : VehicleRepository {

    override fun vehicleExists(id: String): Boolean {
        val vehicle = DbEstacionamiento.getInstance(context).vehicleDao().getVehicleForPlate(id)
        if (vehicle != null) return true
        return false
    }

    override fun insertDataBase(vehicleEntity: VehicleEntity): Long {
        return DbEstacionamiento.getInstance(context).vehicleDao().insert(vehicleEntity.fromModels())
    }

}