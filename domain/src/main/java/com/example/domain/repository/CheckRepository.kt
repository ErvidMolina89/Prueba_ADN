package com.example.domain.repository

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity

interface CheckRepository {
    fun getAllCheck(): MutableList<VehicleAggregate>
    fun updateCheck(checkEntity: CheckEntity)
    fun insertChechVehicle(checkEntity: CheckEntity): Long
}
