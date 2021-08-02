package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromModels
import com.example.infrastructure.data_access.mapper.fromMutableAggregate

class CheckRepoRoom (private val context: Context) : CheckRepository {

    override fun getAllCheck(): MutableList<VehicleAggregate> {
        return DbEstacionamiento.getInstance(context).checkDao().getAllConsultCheckAndVehicleId().fromMutableAggregate()
    }

    override fun insertChechVehicle(checkEntity: CheckEntity): Long {
        return DbEstacionamiento.getInstance(context).checkDao().insert(checkEntity.fromModels())
    }

    override fun getCheckModelsPlateId(plate: String): Boolean {
        val check = DbEstacionamiento.getInstance(context).checkDao().getCheckModelsPlateId(plate)
        check.forEach {
            if (it.totalCost == null) return true
        }
        return false
    }

    override fun updateCheck(checkEntity: CheckEntity) {
        DbEstacionamiento.getInstance(context).checkDao().update(checkEntity.fromModels())
    }

}