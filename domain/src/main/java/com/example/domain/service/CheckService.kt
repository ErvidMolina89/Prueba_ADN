package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.PriceRepository
import com.example.domain.util.convertToFormatDate
import com.example.domain.util.dateDifference
import com.example.domain.value_object.PricesValueObj

class CheckService (
    private val repositoryCheck: CheckRepository,
    private val repositoryPrice: PriceRepository
    ) {

    private var cost: Double? = null

    fun getAllCheck():MutableList<VehicleAggregate>{
        return repositoryCheck.getAllCheck()
    }

    fun insertChechVehicle(checkEntity: CheckEntity): Long {
        return repositoryCheck.insertChechVehicle(checkEntity)
    }

    fun validateCosteVehicle(check: VehicleAggregate): CheckEntity {
        val costHours = repositoryPrice.getPricesForTypeIdAndPriceId(check.typeId!!, 2)
        val costDays = repositoryPrice.getPricesForTypeIdAndPriceId(check.typeId!!, 1)
        val time = check.checkEntity?.dateInput?.convertToFormatDate()?.dateDifference()

        if(validateTimeLessOneHour(check, costHours, time!!)) {
            check.checkEntity!!.totalCost = cost
        }else {
            validateTotalCost(costDays, costHours, check.typeId!!, time, check.cylinder)
            check.checkEntity!!.totalCost = cost
        }
        repositoryCheck.updateCheck(check.checkEntity!!)
        return check.checkEntity!!
    }

    private fun validateTimeLessOneHour(check: VehicleAggregate, price: PricesValueObj, time: MutableList<Int>): Boolean{
        if (time.first() == 0 && time.last() == 0) {
            when (check.typeId) {
                1 -> {
                    cost = price.value!!
                }
                2 -> {
                    if (check.cylinder != null && check.cylinder!!.toInt() >= 500) {
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

    private fun validateTotalCost(day:PricesValueObj, hour:PricesValueObj, type: Int, time: MutableList<Int>, cylinder: String?){
        when (type) {
            1 -> {
                cost = (time[0] * day.value!!) + (time[1] * hour.value!!)
            }
            2 -> {
                if (cylinder != null && cylinder.toInt() >= 500) {
                    cost = (time[0] * day.value!!) + (time[1] * hour.value!!) + day.extra!!
                    return
                }
                cost = (time[0] * day.value!!) + (time[1] * hour.value!!)
            }
        }
    }
}