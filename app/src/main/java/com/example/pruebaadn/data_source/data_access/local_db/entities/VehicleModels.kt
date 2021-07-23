package com.example.pruebaadn.data_source.data_access.local_db.entities

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels
import com.example.pruebaadn.models.Base

@Entity(foreignKeys = [
    ForeignKey(entity = TypeVehicleModels::class,
        parentColumns = ["Id"],
        childColumns = ["TypeId"])
])
data class VehicleModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "TypeId")    var typeId      : Int? = null,
    @ColumnInfo(name = "Plate")     var plate       : String? = null

): Base