package com.ceiba.pruebahiltddd.di_module

import com.ceiba.domain.repository.*
import com.ceiba.infrastructure.data_access.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ModuleRepository {
    @Binds
    abstract fun bindInitVariableRepository(initVariablesRoom: InitializationOfTheDefaultVariablesRoom):
            InitializationOfTheDefaultVariablesRepository
    @Binds
    abstract fun bindCheckRepository(checkRoom: CheckRepoRoom):
            CheckRepository
    @Binds
    abstract fun bindDisponibilityRepository(disponibilityRoom: DisponibilityRepoRoom):
            DisponibilityRepository
    @Binds
    abstract fun bindPriceRepository(priceRoom: PriceRepoRoom):
            PriceRepository
    @Binds
    abstract fun bindVehicleRepository(vehicleRoom: VehicleRepoRoom):
            VehicleRepository
}