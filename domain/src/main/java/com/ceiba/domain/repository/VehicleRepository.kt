package com.ceiba.domain.repository

import com.ceiba.domain.model.VehicleEntity

interface VehicleRepository {

    fun vehicleExists(id: String): Boolean
    fun insertDataBase(vehicleEntity: VehicleEntity): Long
}