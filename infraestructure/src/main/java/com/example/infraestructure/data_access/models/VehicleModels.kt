package com.example.infraestructure.data_access.models

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

@Entity(foreignKeys = [
    ForeignKey(entity = TypeVehicleModels::class,
        parentColumns = ["Id"],
        childColumns = ["TypeId"])
])
data class VehicleModels(
    @PrimaryKey
    @ColumnInfo(name = "Plate")     var plate       : String,
    @ColumnInfo(name = "Cylinder")  var cylinder    : String? = null,
    @ColumnInfo(name = "TypeId")    var typeId      : Int? = null

)