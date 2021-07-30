package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.exception.InvalidDataException
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

fun Date.validateEntryDateVehicle(plate: String): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val day = calendar.get(Calendar.DAY_OF_WEEK)
    val subString = plate.substring(0, 1)
    if (subString == "A" && day == Calendar.SUNDAY && day == Calendar.MONDAY)
        return false
    return true
}