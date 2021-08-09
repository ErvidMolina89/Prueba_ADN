package com.ceiba.domain.builder

import com.ceiba.domain.model.CheckEntity


class CheckEntityBuilder {
    private var plateId: String? = "MAD45F"
    private var dateInput: String?  = "2021-07-31T20:00:00"
    private var dateExit = "2021-07-31T20:00:00"

    fun withPlate(plate: String?): CheckEntityBuilder {
        this.plateId = plate
        return this
    }

    fun withDateInput(dateInput: String?): CheckEntityBuilder {
        this.dateInput = dateInput
        return this
    }

    fun withDateExit(dateExit: String): CheckEntityBuilder {
        this.dateExit = dateExit
        return this
    }

    fun build(): CheckEntity {
        val check = CheckEntity()
        check.CheckEntity(plateId, dateInput, dateExit, null)
        return check
    }

}