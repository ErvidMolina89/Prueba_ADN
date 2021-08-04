package com.example.domain.entity

import com.example.domain.R
import com.example.domain.builder.EntityObjectMother
import com.example.domain.exception.InvalidDataException
import com.example.domain.model.CheckEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CheckEntityTest{

    @Test
    fun checkEntity_CreateCheckWithCorrect_Success(){
        // Arrange
        val expected = EntityObjectMother

        try {
            //Act
            expected.createCheckWithCorrectData()
            //Assert
            assertNotNull(expected)
        }catch (e: InvalidDataException){
        }
    }

    @Test
    fun checkEntity_CreateCheckWithNullPlate_Failure(){
        // Arrange
        val expected = EntityObjectMother

        try {
            //Act
            expected.createCheckkWithNullPlateFailure()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.not_minimun_data_create_object.toString(), e.message)
        }
    }

    @Test
    fun checkEntity_CreateCheckWithNullDateinput_Failure(){
        // Arrange
        val expected = EntityObjectMother

        try {
            //Act
            expected.createCheckkWithNullDateInputFailure()
        }catch (e: InvalidDataException){
            //Assert
            assertEquals(R.string.not_minimun_data_create_object.toString(), e.message)
        }
    }

}