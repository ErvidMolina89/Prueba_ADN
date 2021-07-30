package com.example.application.service

import android.content.Context
import com.example.domain.service.VariantInitService

class VariantInitApplicationService {

    lateinit var variantInintService: VariantInitService

    fun VariantInitApplicationService(variantInintService: VariantInitService){
        this.variantInintService = variantInintService
    }

    fun VariantInit(context: Context){
        variantInintService.variantInit(context)
    }
}