package com.example.domain.entity

import com.example.domain.exception.InvalidDataException
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class VehicleEntityTest{

    @Test
    fun validate_CreateVehcicleWithCorrectPlate_Success(){
        // Data
        val plate = "ABD456"
        val type  = 1
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){
        }
    }

    @Test
    fun validate_CreateVehcicleWithPlateLowercase_Failure(){
        // Data
        val plate = "adh456"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun validate_CreateVehcicleWithPlateLowercaseAndUppercase_Failure(){
        // Data
        val plate = "Fdh456"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun validate_CreateVehcicleWithPlateIncomplete_Failure(){
        // Data
        val plate = "ABD"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun validate_CreateVehcicleWithPlateMoreLetters_Failure(){
        // Data
        val plate = "ABDG45"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun validate_CreateVehcicleWithPlateMoreNumbers_Failure(){
        // Data
        val plate = "AB4545"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun validate_CreateVehcicleWithLongestPlate_Failure(){
        // Data
        val plate = "ABDG456"
        val type  = 1
        val messError = "Format Incorrect Plate"
        val expected = VehicleEntity()

        try {
            //Action
            expected.VehicleEntity(plate, type, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

}