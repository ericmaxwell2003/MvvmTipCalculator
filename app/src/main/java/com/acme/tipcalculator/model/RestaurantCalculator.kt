package com.acme.tipcalculator.model

import junit.framework.Assert
import java.math.RoundingMode

class RestaurantCalculator : Calculator {

    override fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {

        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPct,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

}

