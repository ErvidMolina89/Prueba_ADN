package com.ceiba.infraestructure.data_access

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.infraestructure.data_access.daos.*
import com.ceiba.infraestructure.data_access.models.CheckModels
import com.ceiba.infraestructure.data_access.models.VehicleModels
import com.ceiba.infraestructure.data_access.models.variant_init.DisponibilityModels
import com.ceiba.infraestructure.data_access.models.variant_init.PricesModels
import com.ceiba.infraestructure.data_access.models.variant_init.TypePriceModels
import com.ceiba.infraestructure.data_access.models.variant_init.TypeVehicleModels

@Database(entities = arrayOf(
    DisponibilityModels::class,
    PricesModels::class,
    TypeVehicleModels::class,
    TypePriceModels::class,
    CheckModels::class,
    VehicleModels::class
), version = 1)
abstract class DbEstacionamiento : RoomDatabase() {
    abstract fun disponibilityDao(): DisponibilityDao
    abstract fun pricesDao(): PricesDao
    abstract fun typeVehicleDao(): TypeVehicleDao
    abstract fun typePriceDao(): TypePriceDao
    abstract fun checkDao(): CheckDao
    abstract fun vehicleDao(): VehicleDao

}