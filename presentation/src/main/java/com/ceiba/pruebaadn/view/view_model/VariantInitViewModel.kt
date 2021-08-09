package com.ceiba.pruebaadn.view.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ceiba.application.service.InitializationOfTheDefaultVariablesApplicationService
import com.ceiba.domain.service.InitializationOfTheDefaultVariablesService
import com.ceiba.infrastructure.data_access.repository.InitializationOfTheDefaultVariablesRoom
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VariantInitViewModel (app: Application): AndroidViewModel(app) {
    private lateinit var room: InitializationOfTheDefaultVariablesRoom
    private lateinit var service: InitializationOfTheDefaultVariablesService
    private lateinit var application: InitializationOfTheDefaultVariablesApplicationService

    init {
        room = InitializationOfTheDefaultVariablesRoom(app.applicationContext)
        service = com.ceiba.domain.service.InitializationOfTheDefaultVariablesService(room)
        application  = InitializationOfTheDefaultVariablesApplicationService(service)
    }

    fun validateDbAndInsertVariablesInit(){
        GlobalScope.launch {
            application.InitializationOfTheDefaultVariables()
        }
    }
}