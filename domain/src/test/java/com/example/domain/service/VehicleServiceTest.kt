package com.example.domain.service

import com.example.domain.entity.DisponibilityEntity
import com.example.domain.entity.VehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.DisponibilityRepository
import com.example.domain.repository.VehicleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class VehicleServiceTest{
    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Mock
    lateinit var disponibilityRepository: DisponibilityRepository

    private lateinit var vehicleService: VehicleService

    @Before
    fun setup(){
        initMocks(this)
        vehicleService = VehicleService(vehicleRepository, disponibilityRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleCorrectDifferentPlateLetterA_Success() {
        //Arrange
        val entity = VehicleEntity()
        entity.VehicleEntity("RFR456", 1, null)
        val dateInput = "2021-08-01T12:00:00"
        val disponibility = DisponibilityEntity()
        disponibility.DisponibilityEntity(1, 1, 20)

        `when`(disponibilityRepository.getDisponibilityForTypeId(1)).thenReturn(disponibility)

        try {
            //Act
            val action = vehicleService.insertVehicleDB(entity, dateInput)
            //Assert
            assertNotNull(action)
        }catch (e: InvalidDataException){

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_ExistenceOfVehicleInDB_Failure() {
        //Arrange
        val entity = VehicleEntity()
        entity.VehicleEntity("RFR456", 1, null)
        val dateInput = "2021-08-01T12:00:00"
        val messError = "Vehicle Exist"

        `when`(vehicleRepository.vehicleExists("RFR456")).thenReturn(true)

        try {
            //Act
            vehicleService.insertVehicleDB(entity, dateInput)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleDayDistinctSundayOrMondayPlateLetterA_Failed() {
        //Arrange
        val entity = VehicleEntity()
        entity.VehicleEntity("AFR456", 1, null)
        val dateInput = "2021-07-30T12:00:00"
        val messError = "Vehicle Parking Not Autorize why his Plate Init A"

        try {
            //Act
            vehicleService.insertVehicleDB(entity, dateInput)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertVehicleDB_InsertVehicleDaySundayPlateLetterA_Failed() {
        //Arrange
        val entity = VehicleEntity()
        entity.VehicleEntity("AFR456", 1, null)
        val dateInput = "2021-08-01T12:00:00"
        val disponibility = DisponibilityEntity()
        disponibility.DisponibilityEntity(1, 1, 20)

        `when`(disponibilityRepository.getDisponibilityForTypeId(1)).thenReturn(disponibility)

        try {
            //Act
            val action = vehicleService.insertVehicleDB(entity, dateInput)
            //Assert
            assertNotNull(action)
        }catch (e: InvalidDataException){
        }
    }

}