package com.example.pruebaadn.di.module

import com.example.pruebaadn.di.scope.ApplicationScope
import com.example.pruebaadn.view.NavigationViewModel
import com.example.pruebaadn.view.exit_vehicle.ExitViewModel
import com.example.pruebaadn.view.insert_vehicle.InsertViewModel
import dagger.Module
import dagger.Provides

@Module
class ModuleViewModels {

    @ApplicationScope
    @Provides
    fun provideInsertViewModel() = InsertViewModel()

    @ApplicationScope
    @Provides
    fun provideExitViewModel() = ExitViewModel()

    @ApplicationScope
    @Provides
    fun provideNavigationViewModel() = NavigationViewModel()
}