package com.example.domain.service

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.TypeVehicleRepository
import com.example.domain.repository.VariantInitRepository

class CheckService {
    lateinit var repository: CheckRepository

    fun CheckService(repository: CheckRepository){
        this.repository = repository
    }

    fun getAllCheck(context: Context):MutableList<VehicleAggregate>{
        return repository.getAllCheck(context)
    }

    fun updateCheck(checkEntity: CheckEntity, context: Context) {
        repository.updateCheck(checkEntity, context)
    }

}