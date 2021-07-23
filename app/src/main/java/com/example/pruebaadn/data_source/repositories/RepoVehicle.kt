package com.example.pruebaadn.data_source.repositories

import android.content.Context
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.DbEstacionamiento
import com.example.pruebaadn.data_source.data_access.local_db.entities.DetailsVehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.VehicleModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepoVehicle {

    @Inject
    lateinit var dbEstacionamiento: DbEstacionamiento

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun onInsertVehicle(item: VehicleModels, onSuccessInsert: (Long) -> Unit = {}) {
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.vehicleDao().insert(item))
        }
    }

    fun onInsertDetailsVehicle(item: DetailsVehicleModels, onSuccessInsert: (Long) -> Unit = {}) {
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.detailsVehicleDao().insert(item))
        }
    }

    fun getDetailVehicleForVehicleId(id:Int)
            = dbEstacionamiento.detailsVehicleDao().getDetailVehicleForVehicleId(id)

    fun getVehicleForPlate(plate:String)
            = dbEstacionamiento.vehicleDao().getVehicleForPlate(plate)
    }