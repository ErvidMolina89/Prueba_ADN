package com.example.application.service

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.VehicleEntity
import com.example.domain.service.VehicleService

class VehicleApplicationService {

    lateinit var vehicleService: VehicleService

    fun VehicleApplicationService(vehicleService: VehicleService){
        this.vehicleService = vehicleService
    }

    fun insertVehicleDB(context: Context, vehicle: VehicleAggregate){
        return vehicleService.insertVehicleDB(context, vehicle)
    }
}