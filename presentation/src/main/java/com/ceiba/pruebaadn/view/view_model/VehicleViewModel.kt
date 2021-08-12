package com.ceiba.pruebaadn.view.view_model

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.application.service.VehicleApplicationService
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.view.interfaces.VehicleViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class VehicleViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val application : VehicleApplicationService
): ViewModel() {

    private lateinit var delegate: VehicleViewModelDelegate

    fun insertVehicleDB(vehicle: VehicleEntity, dateInput: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (application.insertDataBase(vehicle, dateInput) != null) {
                        delegate.responseInsertExit()
                    } else delegate.responseException(context.getString(R.string.not_insert_vehicle))
                } catch (e: InvalidDataException) {
                    delegate.responseException(context.getString(e.message!!.toInt()))
                }
            }
        }
    }

    fun setDelegate(delegate: VehicleViewModelDelegate) {
        this.delegate = delegate
    }
}