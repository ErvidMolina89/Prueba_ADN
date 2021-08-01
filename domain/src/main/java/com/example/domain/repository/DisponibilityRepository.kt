package com.example.domain.repository

import com.example.domain.entity.DisponibilityEntity

interface DisponibilityRepository {

    fun getAllDisponibility(): MutableList<DisponibilityEntity>
    fun updateDisponibility(disponibilityEntity: DisponibilityEntity)
    fun getDisponibilityForTypeId(typeId: Int?): DisponibilityEntity

}
