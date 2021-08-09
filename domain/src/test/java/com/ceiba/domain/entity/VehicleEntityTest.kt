package com.ceiba.domain.entity

import com.ceiba.domain.R
import com.ceiba.domain.builder.EntityObjectMother
import com.ceiba.domain.exception.InvalidDataException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class VehicleEntityTest{

    @Test
    fun validatePlateFormat_CreateVehcicleWithCorrectPlate_Success(){
        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarSucces()
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){ }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateLowercase_Failure(){

        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateLowerCaseFailure()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateLowercaseAndUppercase_Failure(){

        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateLowerCaseAndUpperCaseFailure()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateIncomplete_Failure(){

        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarIncomplete()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateMoreLetters_Success(){

        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarWithPlateMoreLettersSuccess()
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){ }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateMoreNumbers_Failure(){
        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarWithPlateMoreNumbersFailure()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithLongestPlate_Failure(){
        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarWithLongestPlate()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateEmpty_Failure(){
        try {
            // Arrange And Act
            val expected = EntityObjectMother.vehiclePlateCarWithPlateEmpty()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

}