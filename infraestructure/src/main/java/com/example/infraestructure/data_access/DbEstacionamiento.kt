package com.example.infraestructure.data_access

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infraestructure.data_access.daos.*
import com.example.infraestructure.data_access.models.CheckModels
import com.example.infraestructure.data_access.models.VehicleModels
import com.example.infraestructure.data_access.models.variant_init.DisponibilityModels
import com.example.infraestructure.data_access.models.variant_init.PricesModels
import com.example.infraestructure.data_access.models.variant_init.TypePriceModels
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

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

    companion object {
        private const val nameDB = "DBEstacinamiento"
        @Volatile
        private var INSTANCE: DbEstacionamiento? = null

        fun getInstance(context: Context): DbEstacionamiento =
                INSTANCE ?: synchronized(this) {
                    buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room
                    .databaseBuilder(context.applicationContext, DbEstacionamiento::class.java, nameDB)
                    .fallbackToDestructiveMigration()
                    .build()
    }
}