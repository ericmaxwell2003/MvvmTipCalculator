package com.acme.tipcalculator.model

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTips() {

        val baseTipCalcualtion =
                TipCalculation(checkAmount = 10.0, tipAmount = 0.0, grandTotal = 0.0)

        assertThat(calculator.calculateTip(checkAmount = 10.0, tipPct = 10),
                equalTo(baseTipCalcualtion.copy(tipAmount = 1.0, grandTotal = 11.0)))
        assertThat(calculator.calculateTip(checkAmount = 10.0, tipPct = 15),
                equalTo(baseTipCalcualtion.copy(tipAmount = 1.5, grandTotal = 11.5)))
        assertThat(calculator.calculateTip(checkAmount = 10.0, tipPct = 20),
                equalTo(baseTipCalcualtion.copy(tipAmount = 2.0, grandTotal = 12.0)))

    }


}