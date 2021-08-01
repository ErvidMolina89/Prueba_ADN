package com.example.pruebaadn.view.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.application.service.CheckApplicationService
import com.example.application.service.VariantInitApplicationService
import com.example.application.service.VehicleApplicationService
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.VehicleEntity
import com.example.domain.service.CheckService
import com.example.domain.service.VariantInitService
import com.example.domain.service.VehicleService
import com.example.infrastructure.data_access.repository.CheckRepoRoom
import com.example.infrastructure.data_access.repository.PriceRepoRoom
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import com.example.infrastructure.data_access.repository.VehicleRepoRoom
import com.example.pruebaadn.view.interfaces.CheckViewModelDelegate
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckViewModel (app: Application): AndroidViewModel(app) {

    private lateinit var delegate: CheckViewModelDelegate
    private lateinit var roomCheck : CheckRepoRoom
    private lateinit var roomPrice : PriceRepoRoom
    private lateinit var service : CheckService
    private lateinit var application : CheckApplicationService

    init {
        roomCheck = CheckRepoRoom(app.applicationContext)
        roomPrice = PriceRepoRoom(app.applicationContext)
        service = CheckService(roomCheck, roomPrice)
        application  = CheckApplicationService(service)
    }

    fun getAllCheck(){
        GlobalScope.launch {
            val list = application.getAllCheck()
            if (list.size == 0){
                delegate.responseEmptyAllCheck()
            } else delegate.responseGetAllCheck(list)
        }
    }

    fun insertCheckVehicle(checkEntity: CheckEntity) {
        GlobalScope.launch {
            if (application.insertChechVehicle(checkEntity) != null) {
                delegate.responseInsertCheckVehicle()
            }else delegate.responseException("No se pudo insertar el Vehiculo")
        }
    }

    fun setDelegate(delegate: CheckViewModelDelegate) {
        this.delegate = delegate
    }

    fun validateCosteVehicle(checkAggregate: VehicleAggregate) {
        GlobalScope.launch {
            val response = application.validateCosteVehicle(checkAggregate)
            if (response.totalCost != null) {
                delegate.responseValidateCosteVehicle(response)
            }else delegate.responseException("No se pudo insertar el Vehiculo")
        }
    }
}