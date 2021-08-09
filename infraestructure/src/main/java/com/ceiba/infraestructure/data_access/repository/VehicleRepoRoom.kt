package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infrastructure.data_access.mapper.fromModels

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