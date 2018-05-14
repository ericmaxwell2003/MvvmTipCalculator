package com.acme.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.acme.tipcalculator.BR
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""
    var locationName = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc

        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
        locationName = tc.locationName
    }

    fun calculateTip() {

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if(checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            notifyChange()
        }

    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
        notifyChange()
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return calculator.loadSavedTipCalculations()
    }

    fun loadTipCalculation(tc: TipCalculation) {
        inputCheckAmount = tc.checkAmount.toString()
        inputTipPercentage = tc.tipPct.toString()

        updateOutputs(tc)
        notifyChange()
    }
}