package com.example.pruebaadn.utils

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ExtensionsDateKtTest {

    @Test
    fun validateDifferenceDate(){
        assertEquals(true, validateSubstractionDate(1, Date()))
    }

    fun dateBefore(lessDay: Int, date: Date): Date{
        val calendar: Calendar  = Calendar.getInstance();
        calendar.setTime(date)
        calendar.add(Calendar.DAY_OF_YEAR, -lessDay)
        return calendar.getTime()
    }

    fun validateSubstractionDate(int: Int, date: Date): Boolean{
        val before = dateBefore(int, date)
        val diff = before.dateDifference()[0]
        if (diff == int){
            return true
        }
        return false
    }

}