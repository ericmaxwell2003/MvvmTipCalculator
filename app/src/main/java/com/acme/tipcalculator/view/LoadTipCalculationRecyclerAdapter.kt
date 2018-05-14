package com.acme.tipcalculator.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.acme.tipcalculator.R
import com.acme.tipcalculator.databinding.SavedTipCalculationsListItemBinding
import com.acme.tipcalculator.model.TipCalculation

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

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SavedTipCalculationsListItemBinding>(
                inflater, R.layout.saved_tip_calculations_list_item, parent, false)
        return LoadTipCalculationViewHolder(binding)

    }

    inner class LoadTipCalculationViewHolder(val binding: SavedTipCalculationsListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(tipCalc: TipCalculation) {
            binding.vm = tipCalc
            binding.root.setOnClickListener { onTipCalcSelected(tipCalc) }
            binding.executePendingBindings()
        }
    }

}