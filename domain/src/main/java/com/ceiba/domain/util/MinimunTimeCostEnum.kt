package com.ceiba.domain.util

enum class MinimunTimeCostEnum {
    VALUEZERO,
    MOTOCYCLE
    ;
    fun getTags() : Int{
        return when(this){
            VALUEZERO -> 0
            MOTOCYCLE -> 2
        }
    }
}