package com.acme.tipcalculator.viewmodel

import android.databinding.BaseObservable
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) : BaseObservable() {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount != null && tipPct != null) {
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
            notifyChange()
        }

    }
}

private const val TAG = "CalculatorViewModel"