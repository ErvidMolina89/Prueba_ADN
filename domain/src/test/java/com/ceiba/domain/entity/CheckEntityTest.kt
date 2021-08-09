package com.ceiba.domain.entity

import com.ceiba.domain.R
import com.ceiba.domain.builder.EntityObjectMother
import com.ceiba.domain.exception.InvalidDataException
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