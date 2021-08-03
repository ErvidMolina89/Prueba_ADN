package com.example.domain.repository

import com.example.domain.model.VehicleEntity

interface VehicleRepository {

    fun vehicleExists(id: String): Boolean
    fun insertDataBase(vehicleEntity: VehicleEntity): Long
}