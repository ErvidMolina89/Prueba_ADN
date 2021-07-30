package com.example.application.service

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.service.PriceService
import com.example.domain.service.TypeVehicleService
import com.example.domain.service.VariantInitService
import com.example.domain.value_object.PricesValueObj

class PriceApplicationService {

    lateinit var service: PriceService

    fun PriceApplicationService(service: PriceService){
        this.service = service
    }

    fun getAllPrices(context: Context): MutableList<PricesValueObj>{
        return service.getAllPrices(context)
    }
}