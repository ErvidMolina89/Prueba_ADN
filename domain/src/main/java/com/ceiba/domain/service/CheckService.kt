package com.ceiba.domain.service

import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.cost_type_vehicle.CostFactory
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.repository.CheckRepository
import com.ceiba.domain.repository.PriceRepository
import com.ceiba.domain.util.TypePriceEnum
import com.ceiba.domain.util.convertToFormatDate
import com.ceiba.domain.util.dateDifference
import javax.inject.Inject

class CheckService @Inject constructor(
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