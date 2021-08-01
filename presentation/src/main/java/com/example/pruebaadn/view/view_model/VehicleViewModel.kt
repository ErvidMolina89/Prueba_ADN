package com.example.pruebaadn.view.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.application.service.VariantInitApplicationService
import com.example.application.service.VehicleApplicationService
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.VehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.service.VariantInitService
import com.example.domain.service.VehicleService
import com.example.infrastructure.data_access.repository.CheckRepoRoom
import com.example.infrastructure.data_access.repository.DisponibilityRepoRoom
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import com.example.infrastructure.data_access.repository.VehicleRepoRoom
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VehicleViewModel (app: Application): AndroidViewModel(app) {

    private lateinit var delegate: VehicleViewModelDelegate
    private lateinit var roomVehicle : VehicleRepoRoom
    private lateinit var roomDisponibility : DisponibilityRepoRoom
    private lateinit var service : VehicleService
    private lateinit var application : VehicleApplicationService

    init {
        roomVehicle = VehicleRepoRoom(app.applicationContext)
        roomDisponibility = DisponibilityRepoRoom(app.applicationContext)
        service = VehicleService(roomVehicle, roomDisponibility)
        application  = VehicleApplicationService(service)
    }

    fun insertVehicleDB(vehicle: VehicleEntity, dateInput: String){
        try {
            GlobalScope.launch {
                if (application.insertVehicleDB(vehicle, dateInput) != null) {
                    delegate.responseInsertExit()
                } else delegate.responseException("No se pudo insertar el Vehiculo")
            }
        }catch (e: InvalidDataException){
            delegate.responseException(e.message)
        }
    }

    fun setDelegate(delegate: VehicleViewModelDelegate) {
        this.delegate = delegate
    }
}