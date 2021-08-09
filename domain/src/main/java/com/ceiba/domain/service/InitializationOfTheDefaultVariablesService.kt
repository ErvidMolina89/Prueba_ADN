package com.ceiba.domain.service

import com.ceiba.domain.repository.InitializationOfTheDefaultVariablesRepository

class InitializationOfTheDefaultVariablesService (private val repository: InitializationOfTheDefaultVariablesRepository) {

    fun InitializationOfTheDefaultVariables(){
        repository.InitializationOfTheDefaultVariables()
    }

}