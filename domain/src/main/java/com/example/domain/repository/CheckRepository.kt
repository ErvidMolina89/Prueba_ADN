package com.example.domain.repository

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.TypeVehicleEntity

interface CheckRepository {
    fun insertCheckDB(context: Context, checkEntity: CheckEntity?): Long
    fun getAllCheck(context: Context): MutableList<VehicleAggregate>
    fun updateCheck(checkEntity: CheckEntity, context: Context)
}
