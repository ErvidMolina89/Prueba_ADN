package com.example.domain.entity

import com.example.domain.exception.InvalidDataException
import org.junit.Assert.*
import org.junit.Test

class CheckEntityTest{

    @Test
    fun CheckEntity_CreateCheckWithCorrect_Success(){
        // Arrange
        val expected = CheckEntity()

        try {
            //Act
            expected.CheckEntity("AFG456", "2021-08-01T20:00:00", null, null)
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){
        }
    }

    @Test
    fun CheckEntity_CreateCheckWithNullPlate_Failure(){
        // Arrange
        val expected = CheckEntity()
        val messError = "No se cuenta con los datos minimos para crear el objeto"

        try {
            //Act
            expected.CheckEntity(null, "2021-08-01T20:00:00", null, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

    @Test
    fun CheckEntity_CreateCheckWithNullDateinput_Failure(){
        // Arrange
        val expected = CheckEntity()
        val messError = "No se cuenta con los datos minimos para crear el objeto"

        try {
            //Act
            expected.CheckEntity("GHT456", null, null, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(messError, e.message)
        }
    }

}