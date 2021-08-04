package com.example.domain.service

import com.example.domain.R
import com.example.domain.builder.EntityObjectMother
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.VehicleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class VehicleServiceTest{

    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Mock
    lateinit var checkRepository: CheckRepository

    @Mock
    lateinit var disponibilityRepository: DisponibilityRepository

    private lateinit var vehicleService: VehicleService

    @Before
    fun setup(){
        initMocks(this)
        vehicleService = VehicleService(vehicleRepository, checkRepository, disponibilityRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleCorrectDifferentPlateLetterA_Success() {
        //Arrange
        val entity = EntityObjectMother
        val dateInput = "2021-08-01T12:00:00"
        `when`(disponibilityRepository.getDisponibilityForTypeId(1)).thenReturn(entity.disponibilityCar())

        try {
            //Act
            val action = vehicleService.insertDataBase(entity.insertVehicleCorrect(), dateInput)
            //Assert
            assertNotNull(action)
        }catch (e: InvalidDataException){

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_ExistenceOfVehicleInDB_Failure() {
        //Arrange
        val entity = EntityObjectMother
        val dateInput = "2021-08-01T12:00:00"

        `when`(vehicleRepository.vehicleExists("RFR456")).thenReturn(true)

        try {
            //Act
            vehicleService.insertDataBase(entity.insertVehicleCorrect(), dateInput)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.vehicle_exists.toString(), e.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleDayDistinctSundayOrMondayPlateLetterA_Failed() {
        //Arrange
        val entity = EntityObjectMother
        val dateInput = "2021-07-30T12:00:00"

        try {
            //Act
            vehicleService.insertDataBase(entity.vehiclePlateCarSucces(), dateInput)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.autorize_not_parking.toString(), e.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleDaySundayPlateLetterA_Failed() {
        //Arrange
        val entity = EntityObjectMother
        val dateInput = "2021-08-01T12:00:00"

        `when`(disponibilityRepository.getDisponibilityForTypeId(1)).thenReturn(entity.disponibilityCar())

        try {
            //Act
            val action = vehicleService.insertDataBase(entity.vehiclePlateCarSucces(), dateInput)
            //Assert
            assertNotNull(action)
        }catch (e: InvalidDataException){
        }
    }

}