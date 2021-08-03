package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromModels
import com.example.infrastructure.data_access.mapper.fromMutableAggregate

class CheckRepoRoom (private val context: Context) : CheckRepository {

    override fun getAll(): MutableList<VehicleAggregate> {
        return DbEstacionamiento.getInstance(context).checkDao().getAllConsultCheckAndVehicleId().fromMutableAggregate()
    }

    override fun insertInvoice(checkEntity: CheckEntity): Long {
        return DbEstacionamiento.getInstance(context).checkDao().insert(checkEntity.fromModels())
    }

    override fun getModelsForPlateId(plate: String): Boolean {
        val check = DbEstacionamiento.getInstance(context).checkDao().getCheckModelsPlateId(plate)
        check.forEach {
            if (it.totalCost == null) return true
        }
        return false
    }

    override fun updateInvoice(checkEntity: CheckEntity) {
        DbEstacionamiento.getInstance(context).checkDao().update(checkEntity.fromModels())
    }

}