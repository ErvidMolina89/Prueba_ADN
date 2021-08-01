package com.example.infraestructure.data_access.models

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(foreignKeys = [
    ForeignKey(entity = VehicleModels::class,
        parentColumns = ["Plate"],
        childColumns = ["PlateId"])
])
data class  CheckModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "PlateId")   var plateId     : String? = null,
    @ColumnInfo(name = "DateInput") var dateInput   : String? = null,
    @ColumnInfo(name = "DateExit")  var dateExit    : String? = null,
    @ColumnInfo(name = "TotalCost") var totalCost   : Double? = null

)