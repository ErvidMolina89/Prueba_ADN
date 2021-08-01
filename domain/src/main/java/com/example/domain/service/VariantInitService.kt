package com.example.domain.service

import android.content.Context
import com.example.domain.repository.VariantInitRepository

class VariantInitService (private val repository: VariantInitRepository) {

    fun variantInit(){
        repository.variantInitRepository()
    }

}