package com.example.domain.entity

import com.example.domain.R

class DisponibilityEntity {

    var id : Int? = null
    var typeId: Int? = null
    var count : Int? = null

    fun DisponibilityEntity(id: Int, typeId: Int, count: Int){
        this.id = id
        this.typeId = typeId
        this.count = count
    }
}