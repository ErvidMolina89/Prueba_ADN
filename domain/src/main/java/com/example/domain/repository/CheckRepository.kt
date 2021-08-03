package com.example.domain.repository

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity

interface CheckRepository {
    fun getAll(): MutableList<VehicleAggregate>
    fun updateInvoice(checkEntity: CheckEntity)
    fun insertInvoice(checkEntity: CheckEntity): Long
    fun getModelsForPlateId(plate: String): Boolean
}
