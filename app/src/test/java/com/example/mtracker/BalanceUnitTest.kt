package com.example.mtracker

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BalanceUnitTest {

    @Test
    fun calculateTotalBalance(){
        // Calculate total balance testing
        val totalBalance = calculateTotalBalance()
        val expectedTotalBalance = 1000.0 // Provide the expected total balance
        assertEquals(expectedTotalBalance, totalBalance)

    }

    @Test
    fun calculateTotalExpenses(){
        // Calculate total expenses testing
        val totalExpenses = calculateTotalExpenses()
        val expectedTotalExpenses = 500.0 // Provide the expected total expenses
        assertEquals(expectedTotalExpenses, totalExpenses)

        println("All tests passed successfully")

    }

}