package com.ceiba.domain.service

import com.ceiba.domain.repository.InitializationOfTheDefaultVariablesRepository
import javax.inject.Inject

class InitializationOfTheDefaultVariablesService @Inject constructor(private val repository: InitializationOfTheDefaultVariablesRepository) {

    fun InitializationOfTheDefaultVariables(){
        repository.InitializationOfTheDefaultVariables()
    }

}