package com.acme.tipcalculator.model

interface Calculator {
    fun calculateTip(checkAmount: Double, tip: Int) : TipCalculation
}

class RestaurantCalculator : Calculator {

    override fun calculateTip(checkAmount: Double, tip: Int) : TipCalculation {

        val tipAmount = checkAmount * (tip.toDouble() / 100.0)
        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

}