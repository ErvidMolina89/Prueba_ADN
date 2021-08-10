package com.ceiba.application.service

import com.ceiba.domain.service.InitializationOfTheDefaultVariablesService
import javax.inject.Inject

class InitializationOfTheDefaultVariablesApplicationService @Inject constructor(private val service: InitializationOfTheDefaultVariablesService) {

    fun InitializationOfTheDefaultVariables(){
        service.InitializationOfTheDefaultVariables()
    }
}