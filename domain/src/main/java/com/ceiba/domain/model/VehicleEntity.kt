package com.ceiba.domain.model

import com.ceiba.domain.R
import com.ceiba.domain.exception.InvalidDataException
import java.util.regex.Matcher
import java.util.regex.Pattern

class VehicleEntity {

    private val format = "^[A-Z]{3}[A-Z 0-9]{3}\$"

    var plate : String? = null
    var typeId : Int? = null
    var cylinder: String? = null

    fun VehicleEntity(plate: String, typeId: Int, cylinder: String?){
        this.typeId = typeId
        this.cylinder = cylinder

        if (plate != "" && validatePlateFormat(plate)){
            this.plate = plate
        }else{
            throw InvalidDataException(R.string.format_incorrect_plate.toString())
        }
    }

    private fun validatePlateFormat(plate: String): Boolean {
        val pattern: Pattern = Pattern.compile(format)
        val matcher: Matcher = pattern.matcher(plate)
        return matcher.matches()
    }
}