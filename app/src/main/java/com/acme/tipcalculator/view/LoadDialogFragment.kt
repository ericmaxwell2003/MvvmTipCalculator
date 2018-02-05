package com.acme.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.TipCalculation
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*


class LoadDialogFragment : DialogFragment() {

    interface Callback {
        fun onTipSelected(tipCalc: TipCalculation)
    }

    var itemSelectedCallback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        itemSelectedCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        itemSelectedCallback = null
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

        rv.adapter = LoadTipCalculationRecyclerAdapter(
                savedTipCalculations = listOf(
                        TipCalculation(locationName = "House of Veggie",
                                checkAmount = 20.00, tipPct = 25,
                                tipAmount = 5.00, grandTotal = 25.00),
                        TipCalculation(locationName = "Big Kahuna Burger",
                                checkAmount = 10.00, tipPct = 25,
                                tipAmount = 2.50, grandTotal = 12.50)

                ),
                onTipCalcSelected = { tipCalc ->
                    itemSelectedCallback?.onTipSelected(tipCalc = tipCalc)
                    dismiss()
                }
        )

        return rv
    }

}

