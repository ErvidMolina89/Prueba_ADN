package com.example.domain.util

enum class TypeVehicleEnum {
    CAR,
    MOTOCYCLE
    ;
    fun getTags() : Int{
        return when(this){
            CAR -> 1
            MOTOCYCLE -> 2
        }
    }
}