package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.convertToFormatDate(format: DateFormats = DateFormats.ISO_8601) : Date {
    val convert = SimpleDateFormat(format.getFormat())
    return convert.parse(this)
}

fun String.validateText(): Boolean{
    if (this != ""){
        return true
    }
    return false
}
