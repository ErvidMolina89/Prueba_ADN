package com.example.pruebaadn.view.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.application.service.DisponibilityApplicationService
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.service.DisponibilityService
import com.example.infrastructure.data_access.repository.DisponibilityRepoRoom
import com.example.pruebaadn.base.App
import com.example.pruebaadn.view.interfaces.DisponibilityViewModelDelegate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisponibilityViewModel : ViewModel() {

    private lateinit var delegate: DisponibilityViewModelDelegate
    private lateinit var room: DisponibilityRepoRoom
    private lateinit var service: DisponibilityService
    private lateinit var application: DisponibilityApplicationService

    init {
        room = DisponibilityRepoRoom()
        service = DisponibilityService()
        application  = DisponibilityApplicationService()
        service.DisponibilityService(room)
        application.DisponibilityApplicationService(service)
    }

    fun getAllDisponibility(context: Context){
        GlobalScope.launch {
            val list = application.getAllDisponibility(context)
            delegate.responseGetAllDisponibility(list)
        }
    }

    fun updateDisponibility(disponibilityEntity: DisponibilityEntity) {
        GlobalScope.launch {
            application.updateDisponibility(disponibilityEntity, App.getContext()!!)
        }
    }

    fun validateDisponibility(list: MutableList<DisponibilityEntity>, type: Int) : Boolean{
        when(type){
            1 -> {
                if (list[0].count!! < 0){
                    return false
                }
            }
            2 -> {
                if (list[1].count!! < 0){
                    return false
                }
            }
        }
        return true
    }

    fun setDelegate(delegate: DisponibilityViewModelDelegate) {
        this.delegate = delegate
    }
}