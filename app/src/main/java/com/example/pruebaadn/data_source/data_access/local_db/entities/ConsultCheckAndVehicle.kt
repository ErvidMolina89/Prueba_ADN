package com.example.pruebaadn.data_source.data_access.local_db.entities

import androidx.room.Embedded
import androidx.room.Relation

class ConsultCheckAndVehicle {
    @Embedded
    var checkModels: CheckModels? = null

    @Relation(parentColumn = "VehicleId",
    entityColumn = "Id")
    var vehicleModels: VehicleModels? = null
}