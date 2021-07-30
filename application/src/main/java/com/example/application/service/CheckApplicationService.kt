package com.example.application.service

import android.content.Context
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.service.CheckService

class CheckApplicationService {

    lateinit var service: CheckService

    fun CheckApplicationService(service: CheckService){
        this.service = service
    }

    fun getAllCheck(context: Context): MutableList<VehicleAggregate>{
        return service.getAllCheck(context)
    }

    fun updateCheck(checkEntity: CheckEntity, context: Context) {
        service.updateCheck(checkEntity, context)
    }
}