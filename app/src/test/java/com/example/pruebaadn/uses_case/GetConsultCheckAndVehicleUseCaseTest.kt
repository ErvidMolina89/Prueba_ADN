package com.example.pruebaadn.uses_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pruebaadn.data_source.data_access.local_db.entities.ConsultCheckAndVehicle
import com.example.pruebaadn.data_source.repositories.RepoCheck
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetConsultCheckAndVehicleUseCaseTest {

    @Mock
    lateinit var repoCheck: RepoCheck
    @Mock
    lateinit var observer: Observer<ConsultCheckAndVehicle>
    lateinit var getConsultCheckAndVehicleUseCase: GetConsultCheckAndVehicleUseCase
    @Spy
    lateinit var response : LiveData<ConsultCheckAndVehicle>

    @Before
    fun setUp(){
        getConsultCheckAndVehicleUseCase = GetConsultCheckAndVehicleUseCase()
        getConsultCheckAndVehicleUseCase.repoCheck = repoCheck
    }

    @Test
    fun failedLoadCheckAndVehicleTest(){
        Mockito.`when`(repoCheck.getConsultCheckAndVehicle(1)).thenReturn(response)
        Mockito.`when`(response.observeForever(observer)).thenReturn(null)

        var boolean = false
        getConsultCheckAndVehicleUseCase.invoke(1, {}, {boolean = true})
        Assert.assertEquals(true, boolean)
    }
}