package com.acme.tipcalculator.view

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.TipCalculation
import com.acme.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*

/**
 * TODO Lab 3: Working w/ AC ViewModels, LiveData, RecyclerViews
 *
 * Add/Update the missing pieces of this Fragment to get a handle on the lifecycle aware
 * AC ViewModel, use it to get saved TipCalculations as LiveData and update the recyclerViews
 * TipCalculations when updates are received.
 *
 * See individual sections for hints & instructions.
 *
 * Bonus Questions:
 * - What value are you getting from using an AC ViewModel?
 * - When we ask ViewModelProviders.of(context) in OnAttach for a CalculatorViewModel
 *   is it constructing a new instance?  Why or why not?
 */
class LoadDialogFragment : DialogFragment() {

    interface Callback {
        fun onTipSelected(tipCalc: TipCalculation)
    }

    private var calculatorViewModel: CalculatorViewModel? = null
    private var itemSelectedCallback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        itemSelectedCallback = context as? Callback
        if(context is FragmentActivity) {
            /**
             * TODO Lab 3: Uncomment this line to assign a calculatorViewModel when we attach to the
             *        hosting activity.
             */
            // calculatorViewModel = ViewModelProviders.of(context).get(CalculatorViewModel::class.java)
        }
    }

    override fun onDetach() {
        super.onDetach()
        itemSelectedCallback = null
        calculatorViewModel = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = context?.let { ctx ->

            AlertDialog.Builder(ctx)
                    .setView(createView(ctx))
                    .setNegativeButton(R.string.action_cancel, null)
                    .create()
        }

        return dialog!!
    }

    private fun createView(ctx: Context) : View {

        val rv = LayoutInflater
                .from(ctx)
                .inflate(R.layout.saved_tip_calculations_list, null)
                .recycler_saved_calcs
        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))

        val tcListAdapter = LoadTipCalculationRecyclerAdapter(
                onTipCalcSelected = { tipCalc ->
                    itemSelectedCallback?.onTipSelected(tipCalc = tipCalc)
                    dismiss()
                }
        )
        rv.adapter = tcListAdapter

        /**
         * TODO Lab 3: Uncomment this code block to ask the ViewModel for TipCalculations
         *        as LiveData and update the LoadTipCalculationRecyclerAdapter with the
         *        update list of tips when a change is observed.
         */
        /*
        calculatorViewModel?.loadSavedTipCalculations()?.observe(this, Observer { tips ->
            if(tips != null) {
                tcListAdapter.updateList(tips)
            }
        })
        */

        return rv
    }

}

