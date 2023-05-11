package com.example.mtracker

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class BillUnitTest {

    @Test
    fun testAddBillExpense() {
        val BillExpenseAmount = 50.0
        val addedBillExpense = testReadBillBudget()
        assertEquals(BillExpenseAmount, addedBillExpense)

    }


    @Test
    // Function to update the overall allocated Bill budget for Bill expenses
    fun testUpdateBillBudget() {
        val newBillBudgetAmount = 500.0
        testUpdateBillBudget()
        val updatedBillBudget = testReadBillBudget()
        assertEquals(newBillBudgetAmount, updatedBillBudget)

    }

    @Test
    // Function to read the total budgeted amount for Bill expenses
    fun testReadBillBudget() {
        val currentBillBudget = testReadBillBudget()
        println("Current Bill budget: $currentBillBudget")
    }

    @Test
    // Function to delete the previously set budget for Bill expenses
    fun testDeleteBillBudget() {
        val deletedBillBudget = testDeleteBillBudget()
        assertEquals(0.0, deletedBillBudget)

        println("All tests passed successfully")
    }



}