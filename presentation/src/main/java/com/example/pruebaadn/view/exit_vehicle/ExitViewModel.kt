package com.example.pruebaadn.view.exit_vehicle

import androidx.lifecycle.ViewModel

class ExitViewModel : ViewModel() {

    private var delegate : ExitViewModelDelegate? = null

    fun setDelegate(delegate: ExitViewModelDelegate) {
        this.delegate = delegate
    }

}