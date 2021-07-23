package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.convertToFormatString(format: DateFormats) : String {
    val convert = SimpleDateFormat(format.getFormat(), Locale.getDefault())
    return convert.format(this)
}

fun Date.dateDifference() : MutableList<Int> {
    val today = Date()
    val diff = Math.abs(today.time - this.time)
    val timeDay = ((diff / (1000*60))/1440).toInt()
    val timehora = ((diff / (1000*60))-(1440 * timeDay))/60
    val result = emptyList<Int>().toMutableList()
    result.add(0 , timeDay)
    result.add(1 , timehora.toInt())
    return result
}