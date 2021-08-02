package com.example.pruebaadn.utils

enum class TagsDialogue {
    DialogAddVehicle,
    DialogueCostVehicle
    ;
    fun getTags() : String{
        return when(this){
            DialogAddVehicle -> "DialogAddVehicle"
            DialogueCostVehicle -> "DialogCostVehicle"
        }
    }
}