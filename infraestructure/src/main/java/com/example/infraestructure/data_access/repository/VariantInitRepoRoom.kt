package com.example.infrastructure.data_access.repository

import android.content.Context
import com.example.domain.repository.VariantInitRepository
import com.example.infraestructure.data_access.DbEstacionamiento
import com.example.infraestructure.data_access.data.DataConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VariantInitRepoRoom  (private val context: Context) : VariantInitRepository{

    override fun variantInitRepository() {
        val list = DbEstacionamiento.getInstance(context).typeVehicleDao().getAllTypeVehicleModels()
        if (list.size == 0){
            GlobalScope.launch {
                DbEstacionamiento.getInstance(context).typePriceDao()
                    .insertList(DataConfig().inserTypePrice())
            }
            GlobalScope.launch {
                onSuccessInsert(DbEstacionamiento.getInstance(context).typeVehicleDao()
                    .insertList(DataConfig().inserTypeVehicle()))
            }

        }

    }

    private fun onSuccessInsert(list: List<Long>){
        list.forEach {  }
        GlobalScope.launch {
            DbEstacionamiento.getInstance(context).pricesDao()
                .insertList(DataConfig().inserPrice())
        }
        GlobalScope.launch {
            DbEstacionamiento.getInstance(context).disponibilityDao()
                .insertList(DataConfig().inserDisponibility())
        }
    }
}