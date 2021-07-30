package com.example.domain.service

import android.content.Context
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.TypeVehicleRepository
import com.example.domain.repository.VariantInitRepository

class DisponibilityService {
    lateinit var repository: DisponibilityRepository

    fun DisponibilityService(repository: DisponibilityRepository){
        this.repository = repository
    }

    fun getAllDisponibility(context: Context):MutableList<DisponibilityEntity>{
        return repository.getAllDisponibility(context)
    }

    fun updateDisponibility(disponibilityEntity: DisponibilityEntity, context: Context) {
        repository.updateDisponibility(disponibilityEntity, context)
    }

}