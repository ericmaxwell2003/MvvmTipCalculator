package com.acme.tipcalculator.viewmodel

import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorViewModelTest {

    @InjectMocks
    lateinit var viewModel : CalculatorViewModel

    @Mock
    lateinit var calculator: Calculator

    @Test
    fun testPerformCalculation() {

        val tipCalculationStub =
                TipCalculation(1.0, 0.5, 1.5)

        viewModel.checkAmountInput = 10.0
        viewModel.tipPercentageAmount = 15

        `when`(calculator.calculateTip(10.0, 15))
                .thenReturn(tipCalculationStub)

        viewModel.performCalculation()

        assertThat(viewModel.tipCalculation.get(), equalTo(
                tipCalculationStub))

    }

}