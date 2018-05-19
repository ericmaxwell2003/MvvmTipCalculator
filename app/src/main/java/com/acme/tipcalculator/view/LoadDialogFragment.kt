package com.acme.tipcalculator.view

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.TipCalculation
import com.acme.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*


class LoadDialogFragment : DialogFragment() {

    interface Callback {
        fun onTipSelected(locationName: String)
    }

    private var calculatorViewModel: CalculatorViewModel? = null
    private var loadTipCallback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loadTipCallback = context as? Callback
        if(context is AppCompatActivity) {
            calculatorViewModel = ViewModelProviders.of(context).get(CalculatorViewModel::class.java)
        }
    }

    override fun onDetach() {
        super.onDetach()
        loadTipCallback = null
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

        val adapter = LoadTipCalculationRecyclerAdapter {
            loadTipCallback?.onTipSelected(it.locationName)
            dismiss()
        }
        rv.adapter = adapter

        calculatorViewModel?.loadSavedTipSummaries()?.observe(this, Observer { tips ->
            if(tips != null) {
                adapter.updateList(tips)
            }
        })

        return rv
    }

}

