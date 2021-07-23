package com.example.pruebaadn.data_source.repositories

import android.content.Context
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.DbEstacionamiento
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.PricesModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypePriceModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepoSynchronization {

    @Inject
    lateinit var dbEstacionamiento: DbEstacionamiento

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun onInsertListTypeVehicle(list: MutableList<TypeVehicleModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.typeVehicleDao().insertList(list))
        }
    }

    fun onInsertListTypePrice(list: MutableList<TypePriceModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.typePriceDao().insertList(list))
        }
    }

    fun onInsertListPrices(items: MutableList<PricesModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.pricesDao().insertList(items))
        }
    }

    fun onInsertListDisponibility(items: MutableList<DisponibilityModels>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.disponibilityDao().insertList(items))
        }
    }

    fun onUpdateDisponibility(item : DisponibilityModels){
        GlobalScope.launch {
            dbEstacionamiento.disponibilityDao().update(item)
        }
    }

    fun getAllTypeVehicle()
            = dbEstacionamiento.typeVehicleDao().getAllTypeVehicleModels()

    fun getPricesForTypeId(type: Int)
            = dbEstacionamiento.pricesDao().getPricesForTypeId(type)

    fun getDisponibilityModelsTypeId(type: Int)
        = dbEstacionamiento.disponibilityDao().getDisponibilityModelsTypeId(type)
    }