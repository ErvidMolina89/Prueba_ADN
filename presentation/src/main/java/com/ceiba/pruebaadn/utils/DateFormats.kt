package com.ceiba.pruebaadn.utils

enum class DateFormats {
    ISO_8601,
    HOURS,
    DATE_REDUCED;

    fun getFormat():String {
        return when(this){
            ISO_8601 -> {"yyyy-MM-dd'T'HH:mm:ss"}
            HOURS -> {"HH:mm:ss"}
            DATE_REDUCED -> {"yyyy-MM-dd"}
        }
    }
}