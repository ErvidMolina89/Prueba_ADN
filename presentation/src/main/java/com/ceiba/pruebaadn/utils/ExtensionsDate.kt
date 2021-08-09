package com.ceiba.pruebaadn.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.convertToFormatString(format: DateFormats) : String {
    val convert = SimpleDateFormat(format.getFormat(), Locale.getDefault())
    return convert.format(this)
}