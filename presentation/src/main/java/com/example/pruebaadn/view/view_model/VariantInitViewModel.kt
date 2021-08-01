package com.example.pruebaadn.view.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application.service.VariantInitApplicationService
import com.example.domain.service.VariantInitService
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VariantInitViewModel (app: Application): AndroidViewModel(app) {
    private lateinit var room: VariantInitRepoRoom
    private lateinit var service: VariantInitService
    private lateinit var application: VariantInitApplicationService

    init {
        room = VariantInitRepoRoom(app.applicationContext)
        service = VariantInitService(room)
        application  = VariantInitApplicationService(service)
    }

    fun validateDbAndInsertVariablesInit(){
        GlobalScope.launch {
            application.VariantInit()
        }
    }
}