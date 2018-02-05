package com.acme.tipcalculator.model

class RestaurantCalculator : Calculator {

    override fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {

        val tipAmount = checkAmount * (tipPct.toDouble() / 100.0)

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

}