package com.example.domain.service

import android.content.Context
import com.example.domain.repository.VariantInitRepository

class VariantInitService {
    lateinit var variantInitRepository: VariantInitRepository

    fun VariantInitService(variantInitRepository: VariantInitRepository){
        this.variantInitRepository = variantInitRepository
    }

    fun variantInit(context: Context){
        variantInitRepository.variantInitRepository(context )
    }

}