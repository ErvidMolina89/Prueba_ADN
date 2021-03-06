package com.ceiba.infraestructure.data_access.models.variant_init

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeVehicleModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "Name")      var name        : String? = null

)
