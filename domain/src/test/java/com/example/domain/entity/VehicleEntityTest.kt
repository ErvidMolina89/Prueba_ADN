package com.example.domain.entity

import com.example.domain.R
import com.example.domain.exception.InvalidDataException
import com.example.domain.model.VehicleEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class VehicleEntityTest{

    @Test
    fun validatePlateFormat_CreateVehcicleWithCorrectPlate_Success(){
        // Arrange
        val plate = "ABD456"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateLowercase_Failure(){
        // Arrange
        val plate = "adh456"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateLowercaseAndUppercase_Failure(){
        // Arrange
        val plate = "Fdh456"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateIncomplete_Failure(){
        // Arrange
        val plate = "ABD"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateMoreLetters_Failure(){
        // Arrange
        val plate = "ABDG45"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateMoreNumbers_Failure(){
        // Arrange
        val plate = "AB4545"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithLongestPlate_Failure(){
        // Arrange
        val plate = "ABDG456"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

    @Test
    fun validatePlateFormat_CreateVehcicleWithPlateEmpty_Failure(){
        // Arrange
        val plate = ""
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Act
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.format_incorrect_plate.toString(), e.message)
        }
    }

}