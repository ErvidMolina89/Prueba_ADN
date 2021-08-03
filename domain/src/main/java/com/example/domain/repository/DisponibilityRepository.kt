package com.example.domain.repository

import com.example.domain.model.DisponibilityEntity

interface DisponibilityRepository {

    fun getAll(): MutableList<DisponibilityEntity>
    fun update(disponibilityEntity: DisponibilityEntity)
    fun getDisponibilityForTypeId(typeId: Int?): DisponibilityEntity

}
