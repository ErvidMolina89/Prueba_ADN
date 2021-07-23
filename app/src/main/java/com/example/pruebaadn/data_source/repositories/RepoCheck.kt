package com.example.pruebaadn.data_source.repositories

import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.DbEstacionamiento
import com.example.pruebaadn.data_source.data_access.local_db.entities.CheckModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepoCheck {

    @Inject
    lateinit var dbEstacionamiento: DbEstacionamiento

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun onInsertCheck(item: CheckModels, onSuccessInsert: (Long) -> Unit = {}) {
        GlobalScope.launch {
            onSuccessInsert(dbEstacionamiento.checkDao().insert(item))
        }
    }

    fun onUpdateCheck(item: CheckModels) {
        GlobalScope.launch {
            dbEstacionamiento.checkDao().update(item)
        }
    }


    fun getConsultCheckAndVehicle(id:Int)
            = dbEstacionamiento.checkDao().getConsultCheckAndVehicleId(id)

    }