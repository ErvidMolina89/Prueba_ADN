package com.example.domain.repository

import com.example.domain.entity.VehicleEntity

interface VehicleRepository {

    fun vehicleExists(id: String): Boolean
    fun insertVehicleDB(vehicleEntity: VehicleEntity): Long
}