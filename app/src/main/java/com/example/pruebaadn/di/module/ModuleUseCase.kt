package com.example.pruebaadn.di.module

import com.example.pruebaadn.di.scope.ApplicationScope
import com.example.pruebaadn.uses_case.GetConsultCheckAndVehicleUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleUseCase {

    @ApplicationScope
    @Provides
    fun provideGetConsultCheckAndVehicleUseCase() = GetConsultCheckAndVehicleUseCase()

//    @ApplicationScope
//    @Provides
//    fun provideConsulteForUser() = ConsulteForUserUseCase()

}