package com.acme.tipcalculator.viewmodel

import android.util.Log
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {

        Log.d(TAG, "calculateTipInvoked")

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount != null && tipPct != null) {
            Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
        }

    }
}

private const val TAG = "CalculatorViewModel"