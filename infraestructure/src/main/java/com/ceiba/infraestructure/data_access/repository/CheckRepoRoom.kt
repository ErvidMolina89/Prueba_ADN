package com.ceiba.infrastructure.data_access.repository

import android.content.Context
import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.repository.CheckRepository
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infraestructure.data_access.daos.CheckDao
import com.ceiba.infrastructure.data_access.mapper.fromModels
import com.ceiba.infrastructure.data_access.mapper.fromMutableAggregate
import javax.inject.Inject

class CheckRepoRoom @Inject constructor (private val checkDao: CheckDao) : CheckRepository {

    override fun getAll(): MutableList<VehicleAggregate> {
        return checkDao.getAllConsultCheckAndVehicleId().fromMutableAggregate()
    }

    override fun insertInvoice(checkEntity: CheckEntity): Long {
        return checkDao.insert(checkEntity.fromModels())
    }

    override fun getModelsForPlateId(plate: String): Boolean {
        val check = checkDao.getCheckModelsPlateId(plate)
        check.forEach {
            if (it.totalCost == null) return true
        }
        return false
    }

    override fun updateInvoice(checkEntity: CheckEntity) {
        checkDao.update(checkEntity.fromModels())
    }

}