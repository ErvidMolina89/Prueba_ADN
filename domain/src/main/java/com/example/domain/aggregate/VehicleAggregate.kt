package com.example.domain.aggregate

import com.example.domain.model.CheckEntity

class VehicleAggregate {
    var plate : String? = null
    var typeId : Int? = null
    var cylinder: String? = null
    var checkEntity: CheckEntity? = null

    fun VehicleAggregate(plate: String?, typeId: Int?, cylinder: String?, checkEntity: CheckEntity?){
        this.plate = plate
        this.typeId = typeId
        this.cylinder = cylinder
        this.checkEntity = checkEntity
    }
}