package com.example.domain.service

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.TypeVehicleRepository
import com.example.domain.repository.VariantInitRepository

class TypeVehicleService {
    lateinit var typeVehicleRepository: TypeVehicleRepository

    fun TypeVehicleService(typeVehicleRepository: TypeVehicleRepository){
        this.typeVehicleRepository = typeVehicleRepository
    }

    fun getTypeVehicle(context: Context):MutableList<TypeVehicleEntity>{
        return typeVehicleRepository.getTypeVehicle(context)
    }

}