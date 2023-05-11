package com.example.mtracker

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class EduUnitTest {

    @Test
    fun testAddEducationExpense() {
        val EducationExpenseAmount = 50.0
        val addedEducationExpense = testReadEducationBudget()
        assertEquals(EducationExpenseAmount, addedEducationExpense)

    }


    @Test
    // Function to update the overall allocated Education budget for Education expenses
    fun testUpdateEducationBudget() {
        val newEducationBudgetAmount = 500.0
        testUpdateEducationBudget()
        val updatedEducationBudget = testReadEducationBudget()
        assertEquals(newEducationBudgetAmount, updatedEducationBudget)

    }

    @Test
    // Function to read the total budgeted amount for Education expenses
    fun testReadEducationBudget() {
        val currentEducationBudget = testReadEducationBudget()
        println("Current Education budget: $currentEducationBudget")
    }

    @Test
    // Function to delete the previously set budget for Education expenses
    fun testDeleteEducationBudget() {
        val deletedEducationBudget = testDeleteEducationBudget()
        assertEquals(0.0, deletedEducationBudget)

        println("All tests passed successfully")
    }


}