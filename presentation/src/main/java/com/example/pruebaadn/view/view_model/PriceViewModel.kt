package com.example.pruebaadn.view.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.DisponibilityApplicationService
import com.example.application.service.PriceApplicationService
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.service.DisponibilityService
import com.example.domain.service.PriceService
import com.example.domain.value_object.PricesValueObj
import com.example.infrastructure.data_access.repository.DisponibilityRepoRoom
import com.example.infrastructure.data_access.repository.PriceRepoRoom
import com.example.pruebaadn.base.App
import com.example.pruebaadn.view.interfaces.DisponibilityViewModelDelegate
import com.example.pruebaadn.view.interfaces.PriceViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PriceViewModel : ViewModel() {

    private lateinit var delegate: PriceViewModelDelegate
    private lateinit var room: PriceRepoRoom
    private lateinit var service: PriceService
    private lateinit var application: PriceApplicationService

    init {
        room = PriceRepoRoom()
        service = PriceService()
        application  = PriceApplicationService()
        service.PriceService(room)
        application.PriceApplicationService(service)
    }

    fun getAllPrice(context: Context){
        GlobalScope.launch {
            val list = application.getAllPrices(context)
            delegate.responseAllPrice(list)
        }
    }

    fun validatePrice(list: MutableList<PricesValueObj>, type: Int) : Boolean{

        return true
    }

    fun setDelegate(delegate: PriceViewModelDelegate) {
        this.delegate = delegate
    }
}