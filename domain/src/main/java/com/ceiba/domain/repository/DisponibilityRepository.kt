package com.ceiba.domain.repository

import com.ceiba.domain.model.DisponibilityEntity

interface DisponibilityRepository {

    fun getAll(): MutableList<DisponibilityEntity>
    fun update(disponibilityEntity: DisponibilityEntity)
    fun getDisponibilityForTypeId(typeId: Int?): DisponibilityEntity

}
