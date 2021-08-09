package com.ceiba.domain.model

import com.ceiba.domain.R
import com.ceiba.domain.exception.InvalidDataException

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