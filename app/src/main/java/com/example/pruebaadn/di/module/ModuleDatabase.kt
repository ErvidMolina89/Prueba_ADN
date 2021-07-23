package com.example.pruebaadn.di.module

import android.content.Context
import com.example.pruebaadn.data_source.data_access.local_db.DbEstacionamiento
import com.example.pruebaadn.di.scope.ApplicationScope
import com.example.pruebaadn.uses_case.GetConsultCheckAndVehicleUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleDatabase {

    @ApplicationScope
    @Provides
    fun provideDbEstacionamiento(context: Context) = DbEstacionamiento.getInstance(context)

}