package com.example.pruebaadn.di.component

import com.example.pruebaadn.data_source.repositories.RepoCheck
import com.example.pruebaadn.data_source.repositories.RepoSynchronization
import com.example.pruebaadn.data_source.repositories.RepoVehicle
import com.example.pruebaadn.di.module.*
import com.example.pruebaadn.view.NavigationActivity
import com.example.pruebaadn.uses_case.GetConsultCheckAndVehicleUseCase
import com.example.pruebaadn.view.NavigationViewModel
import com.example.pruebaadn.view.exit_vehicle.ExitFragment
import com.example.pruebaadn.view.exit_vehicle.ExitViewModel
import com.example.pruebaadn.view.insert_vehicle.InsertFragment
import com.example.pruebaadn.view.insert_vehicle.InsertViewModel
import dagger.Component

@Component(modules = [
    ModuleApplication::class,
    ModuleViewModels::class,
    ModuleUseCase::class,
    ModuleRepositories::class,
    ModuleDatabase::class
])
interface ComponentApplication {

    //Activity
    fun inject(navigationActivity: NavigationActivity)

    //ViewModel
    fun inject(navigationViewModel: NavigationViewModel)
    fun inject(insertViewModel: InsertViewModel)
    fun inject(exitViewModel: ExitViewModel)

    //Fragment
    fun inject(insertFragment: InsertFragment)
    fun inject(exitFragment: ExitFragment)

    //Uses Case
    fun inject(getConsultCheckAndVehicleUseCase: GetConsultCheckAndVehicleUseCase)

    //Repositories
    fun inject(repoSynchronization: RepoSynchronization)
    fun inject(repoVehicle: RepoVehicle)
    fun inject(repoCheck: RepoCheck)

}