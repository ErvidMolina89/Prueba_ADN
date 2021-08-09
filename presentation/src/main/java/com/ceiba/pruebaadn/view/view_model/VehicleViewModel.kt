package com.ceiba.pruebaadn.view.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ceiba.application.service.VehicleApplicationService
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.domain.service.VehicleService
import com.ceiba.infrastructure.data_access.repository.CheckRepoRoom
import com.ceiba.infrastructure.data_access.repository.DisponibilityRepoRoom
import com.ceiba.infrastructure.data_access.repository.VehicleRepoRoom
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.base.App
import com.ceiba.pruebaadn.view.interfaces.VehicleViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VehicleViewModel (app: Application): AndroidViewModel(app) {

    private lateinit var delegate: VehicleViewModelDelegate
    private lateinit var roomVehicle : VehicleRepoRoom
    private lateinit var roomCheck : CheckRepoRoom
    private lateinit var roomDisponibility : DisponibilityRepoRoom
    private lateinit var service : VehicleService
    private lateinit var application : VehicleApplicationService

    init {
        roomVehicle = VehicleRepoRoom(app.applicationContext)
        roomCheck = CheckRepoRoom(app.applicationContext)
        roomDisponibility = DisponibilityRepoRoom(app.applicationContext)
        service = VehicleService(roomVehicle, roomCheck, roomDisponibility)
        application  = VehicleApplicationService(service)
    }

    fun insertVehicleDB(vehicle: VehicleEntity, dateInput: String){
        GlobalScope.launch {
            try {
                if (application.insertDataBase(vehicle, dateInput) != null) {
                    delegate.responseInsertExit()
                } else delegate.responseException(App.getContext()?.getString(R.string.not_insert_vehicle))
            } catch (e: InvalidDataException) {
                delegate.responseException(App.getContext()?.getString(e.message!!.toInt()))
            }
        }
    }

    fun setDelegate(delegate: VehicleViewModelDelegate) {
        this.delegate = delegate
    }
}