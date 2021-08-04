package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.builder.EntityObjectMother
import com.example.domain.exception.InvalidDataException
import com.example.domain.model.CheckEntity
import com.example.domain.model.PricesEntity
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.PriceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class CheckServiceTest{

    @Mock
    lateinit var repositoryCheck: CheckRepository

    @Mock
    lateinit var repositoryPrice: PriceRepository

    private lateinit var checkService: CheckService

    @Before
    fun setup() {
        initMocks(this)
        checkService = CheckService(repositoryCheck, repositoryPrice)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleCar_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 1)).thenReturn(aggregate.priceDaysCar())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 2)).thenReturn(aggregate.priceHoursCar())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleOneDayAndTreeHours())
        //Assert
        assertEquals(11000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleCarLessThanAnHour_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 1)).thenReturn(aggregate.priceDaysCar())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 2)).thenReturn(aggregate.priceHoursCar())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleCarLessThanAnHour())
        //Assert
        assertEquals(1000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleMotoMoreCylinder_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(aggregate.priceDayMotocycler())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(aggregate.priceHoursMotocycler())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleMotoMoreCylinder())
        //Assert
        assertEquals(6000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleMoreCylinderMotoLessThanAnHour_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(aggregate.priceDayMotocycler())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(aggregate.priceHoursMotocycler())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleMoreCylinderMotoLessThanAnHour())
        //Assert
        assertEquals(2500.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleLessCylinderMotoLessThanAnHour_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(aggregate.priceDayMotocycler())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(aggregate.priceHoursMotocycler())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleLessCylinderMotoLessThanAnHour())
        //Assert
        assertEquals(500.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleMotoLessCylinder_success() {
        //Arrange
        val aggregate = EntityObjectMother
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(aggregate.priceDayMotocycler())
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(aggregate.priceHoursMotocycler())
        //Act
        val action = checkService.validateCostVehicle(aggregate.costVehicleMotoLessCylinder())
        //Assert
        assertEquals(8000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertChechVehicle_enterCorrectCheckVehicle_success() {
        //Arrange
        val objectMother = EntityObjectMother
        val check = objectMother.enterCorrectCheckVehicleSuccess()
        `when`(repositoryCheck.insertInvoice(check)).thenReturn(1)

        try {
            //Act
            val action = checkService.insertCheckVehicle(check)
            //Assert
            assertEquals(1, action)
        }catch (e: InvalidDataException){

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllCheck_allCheckVehicles_success() {
        //Arrange
        val aggregate = EntityObjectMother
        val list = emptyList<VehicleAggregate>().toMutableList()
        list.add(aggregate.costVehicleOneDayAndTreeHours())
        list.add(aggregate.costVehicleCarLessThanAnHour())

        `when`(repositoryCheck.getAll()).thenReturn(list)
        //Act
        val action = checkService.getAllCheck()
        //Assert
        assertNotNull(action)
        assertEquals(2, action.size)
    }
}