package com.example.pruebaadn.view.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.VariantInitApplicationService
import com.example.domain.service.VariantInitService
import com.example.infrastructure.data_access.repository.VariantInitRepoRoom
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VariantInitViewModel : ViewModel() {

    fun validateDbAndInsertVariablesInit(context: Context){
        val room = VariantInitRepoRoom()
        val service = VariantInitService()
        val application  = VariantInitApplicationService()
        service.VariantInitService(room)
        application.VariantInitApplicationService(service)
        GlobalScope.launch {
            application.VariantInit(context)
        }
    }
}