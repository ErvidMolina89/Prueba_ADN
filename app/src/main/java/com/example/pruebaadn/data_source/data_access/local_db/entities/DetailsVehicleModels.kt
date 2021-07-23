package com.example.pruebaadn.data_source.data_access.local_db.entities

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.pruebaadn.models.Base

@Entity(foreignKeys = [
    ForeignKey(entity = VehicleModels::class,
        parentColumns = ["Id"],
        childColumns = ["VehicleId"])
])
data class DetailsVehicleModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "VehicleId") var vehicleId   : Int? = null,
    @ColumnInfo(name = "Value")     var value       : String? = null

): Base