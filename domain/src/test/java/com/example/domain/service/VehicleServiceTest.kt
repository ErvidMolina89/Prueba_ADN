package com.example.domain.service

import com.example.domain.MainCoroutineRule
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.VehicleRepository
import com.example.domain.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class VehicleServiceTest{
    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Mock
    lateinit var checkRepository: CheckRepository

    @Mock
    lateinit var disponibilityRepository: DisponibilityRepository

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var vehicleService: VehicleService

    @Before
    fun setup(){
        initMocks(this)
        vehicleService = VehicleService(vehicleRepository, disponibilityRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateInsertVehicleCorrectExit() = coroutineRule.runBlockingTest {

    }
}