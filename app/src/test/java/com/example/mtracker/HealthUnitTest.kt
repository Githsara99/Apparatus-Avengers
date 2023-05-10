package com.example.mtracker

import org.junit.Assert.assertEquals
import org.junit.Test

class PostUnitTest {
    @Test
    fun testAddHealthExpense() {
        val healthExpenseAmount = 50.0
        val addedHealthExpense = testReadHealthBudget()
        assertEquals(healthExpenseAmount, addedHealthExpense)

    }


    @Test
    // Function to update the overall allocated health budget for health expenses
    fun testUpdateHealthBudget() {
        val newHealthBudgetAmount = 500.0
        testUpdateHealthBudget()
        val updatedHealthBudget = testReadHealthBudget()
        assertEquals(newHealthBudgetAmount, updatedHealthBudget)

    }

    @Test
    // Function to read the total budgeted amount for health expenses
    fun testReadHealthBudget() {
        val currentHealthBudget = testReadHealthBudget()
        println("Current health budget: $currentHealthBudget")
    }

    @Test
    // Function to delete the previously set budget for health expenses
    fun testDeleteHealthBudget() {
        val deletedHealthBudget = testDeleteHealthBudget()
        assertEquals(0.0, deletedHealthBudget)

        println("All tests passed successfully")
    }


}
