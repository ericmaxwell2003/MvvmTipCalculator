package com.acme.tipcalculator.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

class Calculator(val tcRepository: InMemoryTipCalculationRepository = InMemoryTipCalculationRepository()) {

    fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {

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

    fun saveTipCalculation(tc: TipCalculation) {
        tcRepository.saveTipCalculation(tc)
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return tcRepository.loadSavedTipCalculations()
    }

}

