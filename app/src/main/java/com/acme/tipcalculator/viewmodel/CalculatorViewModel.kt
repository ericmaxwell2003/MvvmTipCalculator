package com.acme.tipcalculator.viewmodel

import android.arch.lifecycle.LiveData
import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.TipCalculation


/**
 * TODO Lab 2: Binding Data
 *
 * Extend the abstract base class BaseObservable so that this class can provide
 * observable data for the view and notify the view when properties change.
 *
 */
/**
 * TODO Lab 3: Working w/ AC ViewModels, LiveData, RecyclerViews
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
class CalculatorViewModel constructor(private val calculator: Calculator = Calculator()) {

    var checkAmtInput = ""
    var tipPctInput = ""

    /**
     * TODO Lab 2: Mark tipCalculation as a Bindable variable so that the view can register
     *        a property change listener and receive updates when this property changes.
     */
    var tipCalculation = TipCalculation()

    fun calculateTip() {

        val checkAmt = checkAmtInput.toDoubleOrNull()
        val tipPctAmt = tipPctInput.toIntOrNull()

        if(checkAmt != null && tipPctAmt != null) {
            tipCalculation = calculator.calculateTip(checkAmt, tipPctAmt)

            /**
             * TODO Lab 2: Add a line below this comment to call the appropriate BaseObservable function
             *        to notify the view that the tipCalculation property has changed.
             */
        }
    }

    fun loadTipCalculation(tc: TipCalculation) {
        checkAmtInput = tc.checkAmount.toString()
        tipPctInput = tc.tipPct.toString()
        tipCalculation = tc

        /**
         * TODO Lab 2: Add a line below this comment to call the appropriate BaseObservable function
         *        to notify the view that all properties of this viewModel have changed.
         */
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = tipCalculation.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        tipCalculation = tipToSave

        /**
         * TODO Lab 2: Add a line below this comment to call the appropriate BaseObservable function
         *        to notify the view that the tipCalculation property has changed.
         */
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return calculator.loadSavedTipCalculations()
    }


}