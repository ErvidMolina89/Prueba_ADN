package com.example.domain.util

import java.util.*

fun Date.dateDifference() : MutableList<Int> {
    val today = Date()
    val diff = Math.abs(today.time - this.time)
    val timeDay = ((diff / (1000*60))/1440).toInt()
    val timehora = ((diff / (1000*60))-(1440 * timeDay))/60
    val result = emptyList<Int>().toMutableList()
    if(timehora.toInt() > 9){
        result.add(0 , (timeDay + 1))
        result.add(1 , 0)
    }else {
        result.add(0 , timeDay)
        result.add(1 , timehora.toInt())
    }
    return result
}