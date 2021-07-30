package com.example.application.service

import android.content.Context
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.service.DisponibilityService

class DisponibilityApplicationService {

    lateinit var service: DisponibilityService

    fun DisponibilityApplicationService(service: DisponibilityService){
        this.service = service
    }

    fun getAllDisponibility(context: Context): MutableList<DisponibilityEntity>{
        return service.getAllDisponibility(context)
    }

    fun updateDisponibility(disponibilityEntity: DisponibilityEntity,context: Context) {
        service.updateDisponibility(disponibilityEntity, context)
    }
}