package com.example.pruebaadn.view.insert_vehicle.view_model

import androidx.lifecycle.ViewModel
import com.example.pruebaadn.view.insert_vehicle.interfaces.InsertViewModelDelegate

class InsertViewModel : ViewModel() {

    private lateinit var delegate: InsertViewModelDelegate

    fun setDelegate(delegate: InsertViewModelDelegate) {
        this.delegate = delegate
    }
}