package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.DisponibilityEntity
import com.ceiba.domain.repository.DisponibilityRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infrastructure.data_access.mapper.fromEntity
import com.ceiba.infrastructure.data_access.mapper.fromListEntity
import com.ceiba.infrastructure.data_access.mapper.fromModels

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