package com.acme.tipcalculator.viewmodel

import android.databinding.ObservableField
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel (private val calculator: Calculator) {

    var checkAmountInput : Double? = null
    var tipPercentageAmount : Int = 10

    var tipCalculation = ObservableField<TipCalculation>()

    fun performCalculation() {

        val tipCalc =  calculator.calculateTip(
                checkAmount = checkAmountInput ?: 0.0,
                tip = tipPercentageAmount)

        tipCalculation.set(tipCalc)

    }

}