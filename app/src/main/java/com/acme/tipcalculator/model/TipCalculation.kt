package com.acme.tipcalculator.model

data class TipCalculation(

        val locationName: String = "",

        val tipPct: Int = 0,
        val checkAmount: Double = 0.0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0)