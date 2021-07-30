package com.example.pruebaadn.utils

enum class TagsDialogue {
    DialogAddVehicle
    ;
    fun getTags() : String{
        return when(this){
            DialogAddVehicle -> "DialogAddVehicle"
        }
    }
}