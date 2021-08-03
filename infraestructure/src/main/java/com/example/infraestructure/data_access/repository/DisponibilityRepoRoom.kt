package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.model.DisponibilityEntity
import com.example.domain.repository.DisponibilityRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromEntity
import com.example.infrastructure.data_access.mapper.fromListEntity
import com.example.infrastructure.data_access.mapper.fromModels

class DisponibilityRepoRoom (private val context: Context) : DisponibilityRepository {

    override fun getAll(): MutableList<DisponibilityEntity>{
        return DbEstacionamiento.getInstance(context).disponibilityDao().getAllDisponibilityModels().fromListEntity()
    }

    override fun update(disponibilityEntity: DisponibilityEntity) {
        DbEstacionamiento.getInstance(context).disponibilityDao().update(disponibilityEntity.fromModels())
    }

    override fun getDisponibilityForTypeId(typeId: Int?): DisponibilityEntity {
        return DbEstacionamiento.getInstance(context).disponibilityDao().getDisponibilityModelsTypeId(typeId!!).fromEntity()
    }

}