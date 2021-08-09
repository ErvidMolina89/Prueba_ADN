package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity

interface CheckRepository {
    fun getAll(): MutableList<VehicleAggregate>
    fun updateInvoice(checkEntity: CheckEntity)
    fun insertInvoice(checkEntity: CheckEntity): Long
    fun getModelsForPlateId(plate: String): Boolean
}
