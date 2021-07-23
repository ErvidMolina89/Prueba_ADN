package com.example.pruebaadn.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.pruebaadn.di.component.ComponentApplication
import com.example.pruebaadn.di.component.DaggerComponentApplication
import com.example.pruebaadn.di.module.ModuleApplication

class App : Application() {

    private var componentApplication: ComponentApplication? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        startComponentDagger()
    }

    // ------------- Methods Private-------------
    // Inicializamos los modulos de dagger
    private fun startComponentDagger(){
        componentApplication = DaggerComponentApplication
            .builder()
            .moduleApplication(ModuleApplication(this))
            .build()
    }

    fun getComponentApplication() = componentApplication

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun getContext() = context
    }
}