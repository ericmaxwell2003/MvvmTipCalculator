package com.acme.tipcalculator.viewmodel

import android.arch.lifecycle.LiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.acme.tipcalculator.BR
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation


/**
 * Lab 3: Working w/ AC ViewModels, LiveData, RecyclerViews
 *
 * Create a custom base class for this and all ViewModels in this project to use.
 * The base class should be:
 * - Abstract
 * - Extend the AC ViewModel()
 * - Implement the DataBinding Observable interface and offer all of the same functionality
 *   that base Observable gave us.
 *
 * Hint: BaseObservable's source code is not hidden.  You can command click to examine what it
 *       is doing... It's okay to copy it.  What happens at DC Boston, stays at DC Boston 😉
 *
 * Bonus Questions:
 *
 */
class CalculatorViewModel constructor(private val calculator: Calculator = Calculator()) : BaseObservable() {

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