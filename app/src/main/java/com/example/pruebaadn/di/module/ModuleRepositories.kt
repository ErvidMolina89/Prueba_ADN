package com.example.pruebaadn.di.module

import com.example.pruebaadn.data_source.repositories.RepoCheck
import com.example.pruebaadn.data_source.repositories.RepoSynchronization
import com.example.pruebaadn.data_source.repositories.RepoVehicle
import com.example.pruebaadn.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ModuleRepositories {

    @ApplicationScope
    @Provides
    fun providesRepoSynchronization() = RepoSynchronization()

    @ApplicationScope
    @Provides
    fun providesRepoVehicle() = RepoVehicle()

    @ApplicationScope
    @Provides
    fun providesRepoCheck() = RepoCheck()
}