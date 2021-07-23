package com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pruebaadn.models.Base

@Entity(foreignKeys = [
    ForeignKey(entity = TypeVehicleModels::class,
        parentColumns = ["Id"],
        childColumns = ["TypeId"])
])
data class DisponibilityModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "TypeId")    var typeId      : Int? = null,
    @ColumnInfo(name = "Count")     var count       : Int? = null

): Base
