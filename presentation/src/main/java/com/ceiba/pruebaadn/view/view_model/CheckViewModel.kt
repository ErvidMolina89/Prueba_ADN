package com.ceiba.pruebaadn.view.view_model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.application.service.CheckApplicationService
import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.service.CheckService
import com.ceiba.infrastructure.data_access.repository.CheckRepoRoom
import com.ceiba.infrastructure.data_access.repository.PriceRepoRoom
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.base.App
import com.ceiba.pruebaadn.view.interfaces.CheckViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class CheckViewModel @Inject constructor(
   @ApplicationContext private val context: Context,
    private val application : CheckApplicationService
): ViewModel() {

    private lateinit var delegate: CheckViewModelDelegate

    fun getAllCheck(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = application.getAllC()
                if (list.size == 0) {
                    delegate.responseEmptyAllCheck()
                } else delegate.responseGetAllCheck(list)
            }
        }
    }

    fun insertCheckVehicle(checkEntity: CheckEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (application.insertInvoice(checkEntity) != null) {
                    delegate.responseInsertCheckVehicle()
                } else delegate.responseException(context.getString(R.string.not_insert_vehicle))
            }
        }
    }

    fun setDelegate(delegate: CheckViewModelDelegate) {
        this.delegate = delegate
    }

    fun validateCosteVehicle(checkAggregate: VehicleAggregate) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = application.validateCosteInvoiceVehicle(checkAggregate)
                if (response.totalCost != null) {
                    delegate.responseValidateCosteVehicle(response)
                } else delegate.responseException(context.getString(R.string.invoice_not_calculated))
            }
        }
    }
}