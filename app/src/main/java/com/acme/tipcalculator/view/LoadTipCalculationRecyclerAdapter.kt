package com.acme.tipcalculator.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.TipCalculation

/**
 * Lab 3: Working w/ AC ViewModels, LiveData, RecyclerViews
 *
 * See individual sections for hints & instructions.
 *
 * Bonus Questions:
 * - What value are you getting from using an AC ViewModel?
 * - When we ask ViewModelProviders.of(context) in OnAttach for a CalculatorViewModel
 *   is it constructing a new instance?  Why or why not?
 */
class LoadTipCalculationRecyclerAdapter(
        val savedTipCalculations: MutableList<TipCalculation> = mutableListOf<TipCalculation>(),
        val onTipCalcSelected: (tc: TipCalculation) -> Unit = {}) :
        RecyclerView.Adapter<LoadTipCalculationRecyclerAdapter.LoadTipCalculationViewHolder>() {

    fun updateList(updates: List<TipCalculation>) {
        savedTipCalculations.clear()
        savedTipCalculations.addAll(updates)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LoadTipCalculationViewHolder, position: Int) {
        holder.bind(savedTipCalculations[position])
    }

    override fun getItemCount() = savedTipCalculations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadTipCalculationViewHolder {

        /**
         * Lab3: Change this to use the static DataBindingUtil inflate function to create
         *       a SavedTipCalculationsListItemBinding, then construct a LoadTipCalculationViewHolder
         *       with that binding instead of the root view.
         */
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.saved_tip_calculations_list_item, parent, false)
        return LoadTipCalculationViewHolder(root)
    }

    /**
     * Lab3: Modify this ViewHolder to take a binding instead of a view.
     *       Store the binding as a member variable.
     *
     * Hint: It still needs to extend RecyclerView.ViewHolder(root: View) which expects
     *       a view passed into it's constructor.  Don't forget that binding gives us
     *       a root property.
     */
    inner class LoadTipCalculationViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(tipCalc: TipCalculation) {
            root.setOnClickListener { onTipCalcSelected(tipCalc) }

            // normally do manual setting of texts and other view state management
            // directly on the views from here.
            /**
             * Lab 3: Add to this function to set the bindings tipCalculation variable
             *        on the binding member val of the viewHolder.
             *
             *        This variable was declared in `saved_tip_calculations_list_item.xml`.
             *        This is why it's part of `SavedTipCalculationsListItemBinding`.
             *
             *  Hint: Make sure the bindings execute right away!!
             */
        }
    }

}