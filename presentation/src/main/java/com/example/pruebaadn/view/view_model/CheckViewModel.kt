package com.example.pruebaadn.view.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.CheckApplicationService
import com.example.application.service.VariantInitApplicationService
import com.example.application.service.VehicleApplicationService
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.VehicleEntity
import com.example.domain.service.CheckService
import com.example.domain.service.VariantInitService
import com.example.domain.service.VehicleService
import com.example.infrastructure.data_access.repository.CheckRepoRoom
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import com.example.infrastructure.data_access.repository.VehicleRepoRoom
import com.example.pruebaadn.view.interfaces.CheckViewModelDelegate
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckViewModel : ViewModel() {

    private lateinit var delegate: CheckViewModelDelegate
    private lateinit var room : CheckRepoRoom
    private lateinit var service : CheckService
    private lateinit var application : CheckApplicationService

    init {
        room = CheckRepoRoom()
        service = CheckService()
        application  = CheckApplicationService()
        service.CheckService(room)
        application.CheckApplicationService(service)
    }

    fun getAllCheck(context: Context){
        GlobalScope.launch {
            val list = application.getAllCheck(context)
            if (list.size == 0){
                delegate.responseEmptyAllCheck()
            }
            delegate.responseGetAllCheck(list)
        }
    }

    fun setDelegate(delegate: CheckViewModelDelegate) {
        this.delegate = delegate
    }

    fun validateCosteVehicle(it: VehicleAggregate) {
        TODO("Not yet implemented")
    }
}