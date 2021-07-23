package com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebaadn.models.Base

@Entity
data class TypeVehicleModels(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "Name")      var name        : String? = null

): Base
