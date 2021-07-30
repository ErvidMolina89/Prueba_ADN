package com.example.pruebaadn.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application()  {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun getContext() = context
        fun setContext(context: Context){
            this.context = context
        }
    }
}