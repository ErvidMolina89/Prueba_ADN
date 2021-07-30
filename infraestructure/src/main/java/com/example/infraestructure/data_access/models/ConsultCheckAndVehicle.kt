package com.example.infraestructure.data_access.models

import androidx.room.Embedded
import androidx.room.Relation

class ConsultCheckAndVehicle {
    @Embedded
    var checkModels: CheckModels? = null

    @Relation(parentColumn = "VehicleId",
    entityColumn = "Plate")
    var vehicleModels: VehicleModels? = null
}