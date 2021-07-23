package com.example.pruebaadn.data_source.data_access.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebaadn.data_source.data_access.local_db.daos.*
import com.example.pruebaadn.data_source.data_access.local_db.entities.CheckModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.DetailsVehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.VehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.PricesModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypePriceModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels

@Database(entities = arrayOf(
    DisponibilityModels::class,
    PricesModels::class,
    TypeVehicleModels::class,
    TypePriceModels::class,
    CheckModels::class,
    DetailsVehicleModels::class,
    VehicleModels::class
), version = 1)
abstract class DbEstacionamiento : RoomDatabase() {
    abstract fun disponibilityDao(): DisponibilityDao
    abstract fun pricesDao(): PricesDao
    abstract fun typeVehicleDao(): TypeVehicleDao
    abstract fun typePriceDao(): TypePriceDao
    abstract fun checkDao(): CheckDao
    abstract fun detailsVehicleDao(): DetailsVehicleDao
    abstract fun vehicleDao(): VehicleDao

    companion object {
        private const val nameDB = "EstacinamientoDB"
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
                    .build()
    }
}