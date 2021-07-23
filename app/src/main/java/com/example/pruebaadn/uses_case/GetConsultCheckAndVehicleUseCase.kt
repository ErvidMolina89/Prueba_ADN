package com.example.pruebaadn.uses_case

import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.entities.ConsultCheckAndVehicle
import com.example.pruebaadn.data_source.repositories.RepoCheck
import javax.inject.Inject

class GetConsultCheckAndVehicleUseCase {

    @Inject
    lateinit var repoCheck: RepoCheck

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun invoke(id: Int, responseSuccess: (ConsultCheckAndVehicle)->Unit, failed: ()->Unit) {
        repoCheck.getConsultCheckAndVehicle(id).observeForever {
            if (it != null) {
                responseSuccess.invoke(it)
            }else failed.invoke()
        }
    }
}