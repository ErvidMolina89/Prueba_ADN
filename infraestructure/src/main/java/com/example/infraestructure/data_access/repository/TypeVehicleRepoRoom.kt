package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.repository.TypeVehicleRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infrastructure.data_access.mapper.fromListModels

class TypeVehicleRepoRoom : TypeVehicleRepository {

    override fun getTypeVehicle(context: Context): MutableList<TypeVehicleEntity>{
        return DbEstacionamiento.getInstance(context).typeVehicleDao().getAllTypeVehicleModels().fromListModels()
    }

}