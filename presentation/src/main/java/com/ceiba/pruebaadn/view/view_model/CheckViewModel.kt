package com.ceiba.pruebaadn.view.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ceiba.application.service.CheckApplicationService
import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.service.CheckService
import com.ceiba.infrastructure.data_access.repository.CheckRepoRoom
import com.ceiba.infrastructure.data_access.repository.PriceRepoRoom
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.base.App
import com.ceiba.pruebaadn.view.interfaces.CheckViewModelDelegate
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
            val list = application.getAllC()
            if (list.size == 0){
                delegate.responseEmptyAllCheck()
            } else delegate.responseGetAllCheck(list)
        }
    }

    fun insertCheckVehicle(checkEntity: CheckEntity) {
        GlobalScope.launch {
            if (application.insertInvoice(checkEntity) != null) {
                delegate.responseInsertCheckVehicle()
            }else delegate.responseException(App.getContext()?.getString(R.string.not_insert_vehicle))
        }
    }

    fun setDelegate(delegate: CheckViewModelDelegate) {
        this.delegate = delegate
    }

    fun validateCosteVehicle(checkAggregate: VehicleAggregate) {
        GlobalScope.launch {
            val response = application.validateCosteInvoiceVehicle(checkAggregate)
            if (response.totalCost != null) {
                delegate.responseValidateCosteVehicle(response)
            }else delegate.responseException(App.getContext()?.getString(R.string.invoice_not_calculated))
        }
    }
}