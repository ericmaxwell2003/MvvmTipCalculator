package com.acme.tipcalculator.viewmodel

import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var viewModel : CalculatorViewModel

    @Mock
    lateinit var mockCalculator: Calculator

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CalculatorViewModel(mockCalculator)
    }

    @Test
    fun testPerformCalculation() {

        val tipCalculationStub =
                TipCalculation(checkAmount = 10.0, tipAmount = 2.0, grandTotal = 12.0)

        `when`(mockCalculator.calculateTip(10.0, 20))
                .thenReturn(tipCalculationStub)


        viewModel.checkAmtInput = 10.0.toString()
        viewModel.tipPctInput = 20.toString()


        viewModel.calculateTip()


        verify(mockCalculator, times(1)).calculateTip(10.0, 20)
        assertThat(viewModel.tipCalculation, equalTo(tipCalculationStub))

    }

}