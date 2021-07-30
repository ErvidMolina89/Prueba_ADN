package com.example.domain.service

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.PriceRepository
import com.example.domain.repository.TypeVehicleRepository
import com.example.domain.repository.VariantInitRepository
import com.example.domain.value_object.PricesValueObj

class PriceService {
    lateinit var repository: PriceRepository

    fun PriceService(repository: PriceRepository){
        this.repository = repository
    }

    fun getAllPrices(context: Context):MutableList<PricesValueObj>{
        return repository.getAllPrices(context)
    }

}