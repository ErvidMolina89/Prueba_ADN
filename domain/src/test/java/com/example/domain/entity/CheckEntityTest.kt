package com.example.domain.entity

import com.example.domain.R
import com.example.domain.exception.InvalidDataException
import com.example.domain.model.CheckEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CheckEntityTest{

    @Test
    fun checkEntity_CreateCheckWithCorrect_Success(){
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
    fun checkEntity_CreateCheckWithNullPlate_Failure(){
        // Arrange
        val expected = CheckEntity()

        try {
            //Act
            expected.CheckEntity(null, "2021-08-01T20:00:00", null, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.not_minimun_data_create_object.toString(), e.message)
        }
    }

    @Test
    fun checkEntity_CreateCheckWithNullDateinput_Failure(){
        // Arrange
        val expected = CheckEntity()

        try {
            //Act
            expected.CheckEntity("GHT456", null, null, null)
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.not_minimun_data_create_object.toString(), e.message)
        }
    }

}