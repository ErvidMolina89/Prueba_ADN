package com.example.domain.model

import com.example.domain.R
import com.example.domain.exception.InvalidDataException

class CheckEntity {
    var id: Int? = null
    var plateId: String? = null
    var dateInput: String? = null
    var dateExit: String? = null
    var totalCost: Double? = null

    fun CheckEntity (plateId: String?, dateInput: String?, dateExit: String?, totalCost: Double?){
        this.dateExit = dateExit
        this.totalCost = totalCost
        if (!plateId.isNullOrEmpty() && !dateInput.isNullOrEmpty()){
            this.plateId = plateId
            this.dateInput = dateInput
        } else throw InvalidDataException(R.string.not_minimun_data_create_object.toString())
    }

}