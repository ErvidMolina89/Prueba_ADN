package com.example.domain.repository

import android.content.Context
import com.example.domain.entity.TypeVehicleEntity

interface TypeVehicleRepository {

    fun getTypeVehicle(context: Context): MutableList<TypeVehicleEntity>

}
