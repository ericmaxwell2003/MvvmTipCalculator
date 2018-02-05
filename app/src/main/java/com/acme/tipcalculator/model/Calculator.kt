package com.acme.tipcalculator.model

interface Calculator {
    fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation
}
