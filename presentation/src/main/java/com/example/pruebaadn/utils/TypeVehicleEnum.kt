package com.example.pruebaadn.utils

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