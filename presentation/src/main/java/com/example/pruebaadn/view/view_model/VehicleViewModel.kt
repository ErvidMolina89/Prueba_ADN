package com.example.pruebaadn.view.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.VariantInitApplicationService
import com.example.application.service.VehicleApplicationService
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.VehicleEntity
import com.example.domain.service.VariantInitService
import com.example.domain.service.VehicleService
import com.example.infrastructure.data_access.repository.CheckRepoRoom
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import com.example.infrastructure.data_access.repository.VehicleRepoRoom
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {

    private lateinit var delegate: VehicleViewModelDelegate
    private lateinit var roomVehicle : VehicleRepoRoom
    private lateinit var roomCheck : CheckRepoRoom
    private lateinit var service : VehicleService
    private lateinit var application : VehicleApplicationService

    init {
        roomVehicle = VehicleRepoRoom()
        roomCheck = CheckRepoRoom()
        service = VehicleService()
        application  = VehicleApplicationService()
        service.VehicleService(roomVehicle)
        service.CheckService(roomCheck)
        application.VehicleApplicationService(service)
    }

    fun insertVehicleDB(context: Context, vehicle: VehicleAggregate){
        GlobalScope.launch {
            val item = application.insertVehicleDB(context, vehicle)
        }
    }

    fun setDelegate(delegate: VehicleViewModelDelegate) {
        this.delegate = delegate
    }
}