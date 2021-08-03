package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.PriceRepository
import com.example.domain.util.TypePriceEnum
import com.example.domain.util.TypeVehicleEnum
import com.example.domain.util.convertToFormatDate
import com.example.domain.util.dateDifference
import com.example.domain.model.PricesEntity

class CheckService (
    private val repositoryCheck: CheckRepository,
    private val repositoryPrice: PriceRepository
    ) {

    private var cost: Double? = null
    private val minimunTimeCost = 0
    private val motorcycleCylinderGreaterThan500 = 500

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

        if(validateTimeThisLessOneHour(check, costHours, time!!)) {
            check.checkEntity!!.totalCost = cost
        }else {
            validateTotalCost(costDays, costHours, check.typeId!!, time, check.cylinder)
            check.checkEntity!!.totalCost = cost
        }
        repositoryCheck.updateInvoice(check.checkEntity!!)
        return check.checkEntity!!
    }

    private fun validateTimeThisLessOneHour(check: VehicleAggregate, price: PricesEntity, time: MutableList<Int>): Boolean{
        if (time.first() == minimunTimeCost && time.last() == minimunTimeCost) {
            when (check.typeId!!) {
                TypeVehicleEnum.CAR.getTags() -> {
                    cost = price.value!!
                }
                TypeVehicleEnum.MOTOCYCLE.getTags() -> {
                    if (check.cylinder != null && check.cylinder!!.toInt() >= motorcycleCylinderGreaterThan500) {
                        cost = price.extra!! + price.value!!
                        return true
                    }
                    cost = price.value!!
                }
            }
            return true
        }
        return false
    }

    private fun validateTotalCost(day: PricesEntity, hour: PricesEntity, type: Int, time: MutableList<Int>, cylinder: String?){
        when (type) {
            TypeVehicleEnum.CAR.getTags() -> {
                cost = (time.first() * day.value!!) + (time.last() * hour.value!!)
            }
            TypeVehicleEnum.MOTOCYCLE.getTags() -> {
                if (cylinder != null && cylinder.toInt() >= motorcycleCylinderGreaterThan500) {
                    cost = (time.first() * day.value!!) + (time.last() * hour.value!!) + day.extra!!
                    return
                }
                cost = (time.first() * day.value!!) + (time.last() * hour.value!!)
            }
        }
    }
}