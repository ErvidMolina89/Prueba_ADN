package com.example.domain.entity


class TypeVehicleEntity {

    var id : Int? = null
    var name : String? = null

    fun TypeVehicleEntity(id: Int, name: String){
        this.id = id
        this.name = name
    }

    override fun toString(): String {
        return name!!
    }
}