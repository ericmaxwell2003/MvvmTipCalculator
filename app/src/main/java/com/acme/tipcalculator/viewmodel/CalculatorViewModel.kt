package com.acme.tipcalculator.viewmodel

import android.databinding.*
import com.acme.tipcalculator.BR
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation
import javax.inject.Inject


class CalculatorViewModel @Inject constructor(private val calculator: Calculator) : BaseObservableViewModel() {

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

    fun loadTipCalc(tc: TipCalculation) {
        checkAmtInput = tc.checkAmount.toString()
        tipPctInput = tc.tipPct.toString()
        tipCalculation = tc
        notifyChange()
    }

}