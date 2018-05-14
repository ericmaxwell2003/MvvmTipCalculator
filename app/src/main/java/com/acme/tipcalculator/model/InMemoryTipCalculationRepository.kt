package com.acme.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

object InMemoryTipCalculationRepository : TipCalculationRepository {

    val savedTips = mutableMapOf<String, TipCalculation>()

    override fun saveTipCalculation(tc: TipCalculation) {
        savedTips[tc.locationName] = tc
    }

    override fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return MutableLiveData<List<TipCalculation>>().apply {
            value = savedTips.values.toList()
        }
    }

}