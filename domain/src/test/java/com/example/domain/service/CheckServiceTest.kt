package com.example.domain.service

import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.model.CheckEntity
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.CheckRepository
import com.example.domain.repository.PriceRepository
import com.example.domain.model.PricesEntity
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
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-07-30T20:00:00"
        val dateExit = "2021-07-31T23:00:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 1, null, checkEntity)
        price.PricesValueObj( 1, 1, 8000.00, null)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 1, 2, 1000.00, null)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(11000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleCarLessThanAnHour_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-07-31T20:00:00"
        val dateExit = "2021-07-31T20:00:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 1, null, checkEntity)
        price.PricesValueObj( 1, 1, 8000.00, null)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 1, 2, 1000.00, null)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(1, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(1000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleMotoMoreCylinder_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-07-31T11:59:00"
        val dateExit = "2021-08-01T10:10:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 2, "650", checkEntity)
        price.PricesValueObj( 2, 1, 4000.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 2, 2, 500.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(6000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleMoreCylinderMotoLessThanAnHour_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-08-01T09:30:00"
        val dateExit = "2021-08-01T10:10:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 2, "650", checkEntity)
        price.PricesValueObj( 2, 1, 4000.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 2, 2, 500.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(2500.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleLessCylinderMotoLessThanAnHour_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-08-01T09:30:00"
        val dateExit = "2021-08-01T10:10:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 2, "450", checkEntity)
        price.PricesValueObj( 2, 1, 4000.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 2, 2, 500.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(500.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun validateCosteVehicle_validateCosteVehicleLessCylinder_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        var price = PricesEntity()
        val dateInput = "2021-08-01T09:10:00"
        val dateExit = "2021-08-02T20:10:00"
        checkEntity.CheckEntity("LKJ456", dateInput, dateExit, null)
        aggregate.VehicleAggregate("LKJ456", 2, "450", checkEntity)
        price.PricesValueObj( 2, 1, 4000.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 1)).thenReturn(price)
        price = PricesEntity()
        price.PricesValueObj( 2, 2, 500.00, 2000.00)
        `when`(repositoryPrice.getPricesForTypeIdAndPriceId(2, 2)).thenReturn(price)
        //Act
        val action = checkService.validateCostVehicle(aggregate)
        //Assert
        assertEquals(8000.0 , action.totalCost)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertChechVehicle_enterCorrectCheckVehicle_success() {
        //Arrange
        val checkEntity = CheckEntity()
        checkEntity.CheckEntity("ADF456","2021-07-30T20:00:00", null, null)
        `when`(repositoryCheck.insertInvoice(checkEntity)).thenReturn(1)

        try {
            //Act
            val action = checkService.insertCheckVehicle(checkEntity)
            //Assert
            assertEquals(1, action)
        }catch (e: InvalidDataException){

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllCheck_allCheckVehicles_success() {
        //Arrange
        val aggregate = VehicleAggregate()
        val checkEntity = CheckEntity()
        val list = emptyList<VehicleAggregate>().toMutableList()
        val dateInput = "2021-08-01T09:10:00"
        checkEntity.CheckEntity("LKJ456", dateInput, null, null)
        aggregate.VehicleAggregate("LKJ456", 2, "450", checkEntity)
        list.add(aggregate)

        `when`(repositoryCheck.getAll()).thenReturn(list)
        //Act
        val action = checkService.getAllCheck()
        //Assert
        assertNotNull(action)
        assertEquals(1, action.size)
    }
}