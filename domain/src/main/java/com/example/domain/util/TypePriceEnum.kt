package com.example.domain.util

enum class TypePriceEnum {
    DAY,
    HOURS
    ;
    fun getTags() : Int{
        return when(this){
            DAY -> 1
            HOURS -> 2
        }
    }
}