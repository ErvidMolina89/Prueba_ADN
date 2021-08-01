package com.example.application.service

import com.example.domain.service.VariantInitService

class VariantInitApplicationService (private val service: VariantInitService) {

    fun VariantInit(){
        service.variantInit()
    }
}