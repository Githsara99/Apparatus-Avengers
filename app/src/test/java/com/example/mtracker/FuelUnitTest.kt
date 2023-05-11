package com.example.mtracker

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class FuelUnitTest {

    fun testAddFuelExpense() {
        val fuelExpenseAmount = 50.0
        val addedFuelExpense = testReadFuelBudget()
        assertEquals(fuelExpenseAmount, addedFuelExpense)

    }


    @Test
    // Function to update the overall allocated fuel budget for fuel expenses
    fun testUpdateFuelBudget() {
        val newFuelBudgetAmount = 500.0
        testUpdateFuelBudget()
        val updatedFuelBudget = testReadFuelBudget()
        assertEquals(newFuelBudgetAmount, updatedFuelBudget)

    }

    @Test
    // Function to read the total budgeted amount for fuel expenses
    fun testReadFuelBudget() {
        val currentFuelBudget = testReadFuelBudget()
        println("Current fuel budget: $currentFuelBudget")
    }

    @Test
    // Function to delete the previously set fuel for fuel expenses
    fun testDeleteFuelBudget() {
        val deletedFuelBudget = testDeleteFuelBudget()
        assertEquals(0.0, deletedFuelBudget)

        println("All tests passed successfully")
    }

}