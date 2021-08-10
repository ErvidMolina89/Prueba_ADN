package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.model.DisponibilityEntity
import com.ceiba.domain.repository.DisponibilityRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infraestructure.data_access.daos.DisponibilityDao
import com.ceiba.infrastructure.data_access.mapper.fromEntity
import com.ceiba.infrastructure.data_access.mapper.fromListEntity
import com.ceiba.infrastructure.data_access.mapper.fromModels
import javax.inject.Inject

class DisponibilityRepoRoom @Inject constructor (private val disponibilityDao: DisponibilityDao) : DisponibilityRepository {

    override fun getAll(): MutableList<DisponibilityEntity>{
        return disponibilityDao.getAllDisponibilityModels().fromListEntity()
    }

    override fun update(disponibilityEntity: DisponibilityEntity) {
        disponibilityDao.update(disponibilityEntity.fromModels())
    }

    override fun getDisponibilityForTypeId(typeId: Int?): DisponibilityEntity {
        return disponibilityDao.getDisponibilityModelsTypeId(typeId!!).fromEntity()
    }

}