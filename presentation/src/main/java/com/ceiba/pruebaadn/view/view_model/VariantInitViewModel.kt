package com.ceiba.pruebaadn.view.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.application.service.InitializationOfTheDefaultVariablesApplicationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VariantInitViewModel @Inject constructor(
    private val service: InitializationOfTheDefaultVariablesApplicationService
) : ViewModel() {


    fun validateDbAndInsertVariablesInit(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                service.InitializationOfTheDefaultVariables()
            }
        }
    }
}