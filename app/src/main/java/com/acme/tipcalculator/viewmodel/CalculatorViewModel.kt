package com.acme.tipcalculator.viewmodel

import android.arch.lifecycle.LiveData
import android.databinding.Bindable
import com.acme.tipcalculator.BR
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel constructor(private val calculator: Calculator = Calculator()) : BaseObservableViewModel() {

    var checkAmtInput = ""
    var tipPctInput = ""

    @Bindable
    var tipCalculation = TipCalculation()

    fun calculateTip() {

        val checkAmt = checkAmtInput.toDoubleOrNull()
        val tipPctAmt = tipPctInput.toIntOrNull()

        if(checkAmt != null && tipPctAmt != null) {
            tipCalculation = calculator.calculateTip(checkAmt, tipPctAmt)
            notifyPropertyChanged(BR.tipCalculation)
        }
    }

    fun loadTipCalculation(tc: TipCalculation) {
        checkAmtInput = tc.checkAmount.toString()
        tipPctInput = tc.tipPct.toString()
        tipCalculation = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = tipCalculation.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        tipCalculation = tipToSave
        notifyPropertyChanged(BR.tipCalculation)
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return calculator.loadSavedTipCalculations()
    }


}