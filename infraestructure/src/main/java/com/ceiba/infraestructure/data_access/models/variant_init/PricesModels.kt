package com.ceiba.infraestructure.data_access.models.variant_init

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = TypeVehicleModels::class,
        parentColumns = ["Id"],
        childColumns = ["TypeId"]),
    ForeignKey(entity = TypePriceModels::class,
        parentColumns = ["Id"],
        childColumns = ["TypePrice"])
])
data class PricesModels (
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "TypeId")    var typeId      : Int? = null,
    @ColumnInfo(name = "TypePrice") var typePrice   : Int? = null,
    @ColumnInfo(name = "Value")     var value       : Double? = null,
    @ColumnInfo(name = "Extra")     var extra       : Double? = null

)
