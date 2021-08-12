package com.ceiba.infrastructure.data_access.repository

import com.ceiba.domain.repository.InitializationOfTheDefaultVariablesRepository
import com.ceiba.infraestructure.data_access.daos.DisponibilityDao
import com.ceiba.infraestructure.data_access.daos.PricesDao
import com.ceiba.infraestructure.data_access.daos.TypePriceDao
import com.ceiba.infraestructure.data_access.daos.TypeVehicleDao
import com.ceiba.infraestructure.data_access.data.DataConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitializationOfTheDefaultVariablesRoom @Inject constructor(
    private val typeVehicleDao: TypeVehicleDao,
    private val typePriceDao: TypePriceDao,
    private val priceDao: PricesDao,
    private val disponibilityDao: DisponibilityDao
) : InitializationOfTheDefaultVariablesRepository{

    override fun InitializationOfTheDefaultVariables() {
        val list = typeVehicleDao.getAllTypeVehicleModels()
        if (list.size == 0){
            GlobalScope.launch {
                typePriceDao
                    .insertList(DataConfig().inserTypePrice())
            }
            GlobalScope.launch {
                onSuccessInsert(typeVehicleDao
                    .insertList(DataConfig().inserTypeVehicle()))
            }

        }

    }

    private fun onSuccessInsert(list: List<Long>){
        list.forEach {  }
        GlobalScope.launch {
            priceDao
                .insertList(DataConfig().inserPrice())
        }
        GlobalScope.launch {
            disponibilityDao
                .insertList(DataConfig().inserDisponibility())
        }
    }
}