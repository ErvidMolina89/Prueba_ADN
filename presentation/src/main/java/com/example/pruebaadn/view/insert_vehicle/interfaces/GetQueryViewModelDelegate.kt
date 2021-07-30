package com.example.pruebaadn.view.insert_vehicle.interfaces

import com.example.domain.entity.TypeVehicleEntity

interface GetQueryViewModelDelegate {
    fun responseGetAllTypeVehicle(list: MutableList<TypeVehicleEntity>)
}