package com.ceiba.domain.util

enum class TypeVehicleEnum {
    CAR,
    MOTOCYCLE,
    MOTOCYCLE_GREATER_500
    ;
    fun getTags() : Int{
        return when(this){
            CAR -> 1
            MOTOCYCLE -> 2
            MOTOCYCLE_GREATER_500 -> 3
        }
    }
}