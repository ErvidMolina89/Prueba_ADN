package com.example.pruebaadn.view

import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pruebaadn.R
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun emptyTest() {
        //Arrange
        writeTo(R.id.editTextSearch, "empty")
        closeSoftKeyboard()

        //Assert
        assertListItemCount(R.id.recyclerViewSearchResults, 0)
    }

    @Test
    fun addVehicletoCarListRecycler(){
        //Arrange
        clickOn(R.id.btn_add_vehicle)
        //Act
        clickOn(R.id.radio_button_car)
        writeTo(R.id.editTextDialoguePlate, "MGT568")
        clickOn(R.id.btn_dialogue_accept)
        Thread.sleep(2000)
        writeTo(R.id.editTextSearch, "MGT")
        //Asssert
        assertContains(R.id.TextViewPlateCard, "MGT568")
        Thread.sleep(2000)
        deleteVeehicleInsert()
    }

    @Test
    fun validateCostCarEnter(){
        //Arrange
        addVehicleValidateCost()
        Thread.sleep(1500)
        //Act
        writeTo(R.id.editTextSearch, "MGT")
        clickOn(R.id.btn_view_cost)
        Thread.sleep(1000)
        //Assert
        assertContains(R.id.details_mess_cost, "1000")
    }

    private fun deleteVeehicleInsert(){
        writeTo(R.id.editTextSearch, "MGT")
        clickOn(R.id.btn_view_cost)
    }

    private fun addVehicleValidateCost(){
        //Arrange
        clickOn(R.id.btn_add_vehicle)
        //Act
        clickOn(R.id.radio_button_car)
        writeTo(R.id.editTextDialoguePlate, "MGT568")
        clickOn(R.id.btn_dialogue_accept)
    }
}