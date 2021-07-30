package com.example.pruebaadn.view.insert_vehicle.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.TypeVehicleApplicationService
import com.example.domain.service.TypeVehicleService
import com.example.infrastructure.data_access.repository.TypeVehicleRepoRoom
import com.example.pruebaadn.view.insert_vehicle.interfaces.GetQueryViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetQueryViewModel : ViewModel() {

    private lateinit var delegate: GetQueryViewModelDelegate

    fun getAllTypeVehicle(context: Context){
        val room = TypeVehicleRepoRoom()
        val service = TypeVehicleService()
        val application  = TypeVehicleApplicationService()
        service.TypeVehicleService(room)
        application.TypeVehicleApplicationService(service)
        GlobalScope.launch {
            val list = application.getTypeVehicle(context)
            delegate.responseGetAllTypeVehicle(list)
        }
    }

    fun setDelegate(delegate: GetQueryViewModelDelegate) {
        this.delegate = delegate
    }
}