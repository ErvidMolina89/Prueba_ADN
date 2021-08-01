package com.example.domain.entity

import com.example.domain.exception.InvalidDataException
import java.util.regex.Matcher
import java.util.regex.Pattern

class VehicleEntity {

    var plate : String? = null
    var typeId : Int? = null
    var cylinder: String? = null

    fun VehicleEntity(plate: String, typeId: Int, cylinder: String?){
        this.typeId = typeId
        this.cylinder = cylinder

        if (validatePlateFormat(plate)){
            this.plate = plate
        }else{
            throw InvalidDataException("Format Incorrect Plate")
        }
    }

    private fun validatePlateFormat(plate: String): Boolean {
        val pattern: Pattern = Pattern.compile("^[A-Z]{3}[A-Z 0-9]{3}\$")
        val matcher: Matcher = pattern.matcher(plate)
        return matcher.matches()
    }
}