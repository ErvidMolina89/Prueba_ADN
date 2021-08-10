package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infraestructure.data_access.daos.VehicleDao
import com.ceiba.infrastructure.data_access.mapper.fromModels
import javax.inject.Inject

class VehicleRepoRoom @Inject constructor (private val vehicleDao: VehicleDao) : VehicleRepository {

    override fun vehicleExists(id: String): Boolean {
        val vehicle = vehicleDao.getVehicleForPlate(id)
        if (vehicle != null) return true
        return false
    }

    override fun insertDataBase(vehicleEntity: VehicleEntity): Long {
        return vehicleDao.insert(vehicleEntity.fromModels())
    }

}