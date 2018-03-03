package com.acme.tipcalculator.model

import junit.framework.Assert
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testCalculateTips() {

        val baseTc = TipCalculation(checkAmount = 10.0)

        listOf(
                baseTc.copy(tipPct = 15, tipAmount = 1.50, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.80, grandTotal = 11.80),
                baseTc.copy(tipPct = 20, tipAmount = 2.00, grandTotal = 12.00)
        ).forEach {
            Assert.assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPct))
        }


    }


}