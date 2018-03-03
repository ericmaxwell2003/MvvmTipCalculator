package com.acme.tipcalculator.model

import android.arch.lifecycle.LiveData

interface TipCalculationRepository {

    fun saveTipCalculation(tc: TipCalculation)

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>>

}