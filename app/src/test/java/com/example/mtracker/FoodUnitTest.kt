package com.example.mtracker

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class FoodUnitTest {
    @Test
    fun testAddFoodExpense(foodExpenseAmount: Double) {
        val foodExpenseAmount = 50.0
        val addedFoodExpense = testReadFoodBudget()
        assertEquals(foodExpenseAmount, addedFoodExpense)

    }


    @Test
    // Function to update the overall allocated food budget for food expenses
    fun testUpdateFoodBudget(newAmount: Double) {
        val newFoodBudgetAmount = 500.0
        testUpdateFoodBudget(newFoodBudgetAmount)
        val updatedFoodBudget = testReadFoodBudget()
        assertEquals(newFoodBudgetAmount, updatedFoodBudget)



    }

    @Test
    // Function to read the total budgeted amount for food expenses
    fun testReadFoodBudget() {
        val currentFoodBudget = testReadFoodBudget()
        println("Current food budget: $currentFoodBudget")
    }

    @Test
    // Function to delete the previously set budget for food expenses
    fun testDeleteFoodBudget() {
        val deletedFoodBudget = testDeleteFoodBudget()
        assertEquals(0.0, deletedFoodBudget)

        println("All tests passed successfully")
    }


}