package com.example.domain.repository

import android.content.Context
import com.example.domain.entity.VehicleEntity

interface VehicleRepository {

    fun vehicleExists(id: String, context: Context): Boolean
    fun insertVehicleDB(context: Context, vehicleEntity: VehicleEntity): Long
}