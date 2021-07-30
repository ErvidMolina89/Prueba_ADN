package com.example.application.service

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.service.TypeVehicleService
import com.example.domain.service.VariantInitService

class TypeVehicleApplicationService {

    lateinit var typeVehicleService: TypeVehicleService

    fun TypeVehicleApplicationService(typeVehicleService: TypeVehicleService){
        this.typeVehicleService = typeVehicleService
    }

    fun getTypeVehicle(context: Context): MutableList<TypeVehicleEntity>{
        return typeVehicleService.getTypeVehicle(context)
    }
}