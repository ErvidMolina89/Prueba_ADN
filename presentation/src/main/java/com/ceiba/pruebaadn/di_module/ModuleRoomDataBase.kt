package com.ceiba.pruebahiltddd.di_module

import android.content.Context
import androidx.room.Room
import com.ceiba.domain.util.AppConstants.DATABASE_NAME
import com.ceiba.infraestructure.data_access.DbEstacionamiento
import com.ceiba.infraestructure.data_access.daos.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRoomDataBase {

    @Singleton
    @Provides
    fun providesRommDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DbEstacionamiento::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCheckDao(db: DbEstacionamiento): CheckDao = db.checkDao()

    @Singleton
    @Provides
    fun provideDisponibilityDao(db: DbEstacionamiento): DisponibilityDao = db.disponibilityDao()

    @Singleton
    @Provides
    fun providePricesDao(db: DbEstacionamiento): PricesDao = db.pricesDao()

    @Singleton
    @Provides
    fun provideTypePriceDao(db: DbEstacionamiento): TypePriceDao = db.typePriceDao()

    @Singleton
    @Provides
    fun provideTypeVehicleDao(db: DbEstacionamiento): TypeVehicleDao = db.typeVehicleDao()

    @Singleton
    @Provides
    fun provideVehicleDao(db: DbEstacionamiento): VehicleDao = db.vehicleDao()
}