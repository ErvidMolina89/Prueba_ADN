package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.cost_type_vehicle.CostFactory
import com.example.domain.model.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.PriceRepository
import com.example.domain.util.TypePriceEnum
import com.example.domain.util.convertToFormatDate
import com.example.domain.util.dateDifference

class CheckService (
    private val repositoryCheck: CheckRepository,
    private val repositoryPrice: PriceRepository
    ) {

    fun getAllCheck():MutableList<VehicleAggregate>{
        return repositoryCheck.getAll()
    }

    fun insertCheckVehicle(checkEntity: CheckEntity): Long {
        return repositoryCheck.insertInvoice(checkEntity)
    }

    fun validateCostVehicle(check: VehicleAggregate): CheckEntity {
        val costHours = repositoryPrice.getPricesForTypeIdAndPriceId(check.typeId!!, TypePriceEnum.HOURS.getTags())
        val costDays = repositoryPrice.getPricesForTypeIdAndPriceId(check.typeId!!, TypePriceEnum.DAY.getTags())
        val time = check.checkEntity?.dateInput?.convertToFormatDate()?.dateDifference(check.checkEntity?.dateExit?.convertToFormatDate()!!)

        check.checkEntity!!.totalCost = CostFactory().getCost(check).calculatedCostVehicle(costDays, costHours, time!!)

        repositoryCheck.updateInvoice(check.checkEntity!!)
        return check.checkEntity!!
    }
}