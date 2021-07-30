package com.example.domain.repository

import android.content.Context
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.TypeVehicleEntity

interface DisponibilityRepository {

    fun getAllDisponibility(context: Context): MutableList<DisponibilityEntity>
    fun updateDisponibility(disponibilityEntity: DisponibilityEntity, context: Context)

}
