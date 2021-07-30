package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromModels
import com.example.infrastructure.data_access.mapper.fromMutableAggregate

class CheckRepoRoom : CheckRepository {

    override fun insertCheckDB(context: Context, checkEntity: CheckEntity?): Long {
        return DbEstacionamiento.getInstance(context).checkDao().insert(checkEntity?.fromModels()!!)
    }

    override fun getAllCheck(context: Context): MutableList<VehicleAggregate> {
        return DbEstacionamiento.getInstance(context).checkDao().getAllConsultCheckAndVehicleId().fromMutableAggregate()
    }

    override fun updateCheck(checkEntity: CheckEntity, context: Context) {
        DbEstacionamiento.getInstance(context).checkDao().update(checkEntity.fromModels())
    }

}