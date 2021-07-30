package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.repository.DisponibilityRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromListModels
import com.example.infrastructure.data_access.mapper.fromModels

class DisponibilityRepoRoom : DisponibilityRepository {

    override fun getAllDisponibility(context: Context): MutableList<DisponibilityEntity>{
        return DbEstacionamiento.getInstance(context).disponibilityDao().getAllDisponibilityModels().fromListModels()
    }

    override fun updateDisponibility(disponibilityEntity: DisponibilityEntity, context: Context) {
        DbEstacionamiento.getInstance(context).disponibilityDao().update(disponibilityEntity.fromModels())
    }

}