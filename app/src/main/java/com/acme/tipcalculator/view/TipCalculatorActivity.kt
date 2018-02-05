package com.acme.tipcalculator.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.acme.tipcalculator.R
import com.acme.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.acme.tipcalculator.model.RestaurantCalculator
import com.acme.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_tip_calculator.*
import android.view.MenuItem
import com.acme.tipcalculator.model.TipCalculation


class TipCalculatorActivity : AppCompatActivity(), LoadDialogFragment.Callback {

    lateinit var viewModel: CalculatorViewModel

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            R.id.action_load -> {
                showLoadDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityTipCalculatorBinding>(this, R.layout.activity_tip_calculator)
        setSupportActionBar(toolbar)

        viewModel = CalculatorViewModel(RestaurantCalculator())
        binding.vm = viewModel
    }

    fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }

    fun showLoadDialog() {
        val loadFragment = LoadDialogFragment()
        loadFragment.show(supportFragmentManager, "LoadDialog")
    }

    override fun onTipSelected(tipCalc: TipCalculation) {
        viewModel.loadTipCalc(tipCalc)
    }

    companion object {
        val LOAD_TIP_REQUEST_CODE = 1
    }

}

