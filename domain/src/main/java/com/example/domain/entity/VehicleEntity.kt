package com.example.domain.entity

class VehicleEntity {

    var plate : String? = null
    var typeId : Int? = null
    var cylinder: String? = null

    fun VehicleEntity(plate: String, typeId: Int, cylinder: String?){
        this.plate = plate
        this.typeId = typeId
        this.cylinder = cylinder
    }
}