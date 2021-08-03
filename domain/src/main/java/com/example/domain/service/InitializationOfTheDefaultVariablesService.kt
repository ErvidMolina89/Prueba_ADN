package com.example.domain.service

import com.example.domain.repository.InitializationOfTheDefaultVariablesRepository

class InitializationOfTheDefaultVariablesService (private val repository: InitializationOfTheDefaultVariablesRepository) {

    fun InitializationOfTheDefaultVariables(){
        repository.InitializationOfTheDefaultVariables()
    }

}