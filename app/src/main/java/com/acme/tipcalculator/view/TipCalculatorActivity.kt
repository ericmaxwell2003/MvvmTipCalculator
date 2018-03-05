package com.acme.tipcalculator.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.TipCalculation
import com.acme.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity(),
        LoadDialogFragment.Callback,
        SaveDialogFragment.Callback {

    /** TODO Lab 1: Add a lateinit var called binding of the generated binding type from `activity_tip_calculator.xml` */


    /**
     * TODO Lab 2: (Optional) Remove this member variable as the binding will have a reference
     *        to the viewModel and we are already storing a class member reference to the binding.
     */
    private lateinit var calculatorViewModel: CalculatorViewModel

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

        /**
         * TODO Lab 3: Uncomment this line to assign a calculatorViewModel using the AC ViewModelProviders
         *        factory method and remove the following one
         */
        // calculatorViewModel = ViewModelProviders.of(context).get(CalculatorViewModel::class.java)
        calculatorViewModel = CalculatorViewModel()

        /**
         * TODO Lab 1: Use the static DataBindingUtil class to set the content view and generate the binding for this
         *        view in one step.
         *        Use this to set the lateinit binding var that you defined at the class level.
         *
         *  Hint: Replace the existing setContentView with the appropriate DataBindingUtil version.
         */
         setContentView(R.layout.activity_tip_calculator)

        /**
         * TODO Lab 1: Access the toolbar from the binding directly instead of using findViewById(..)
         * Bonus Question:  Is this more efficient? why or why not?
         */
        setSupportActionBar(findViewById(R.id.toolbar))

        /** TODO Lab 1: Access the fab from the binding directly instead of using findViewById(..) */
        /**
         * TODO Lab 2: Remove this entire FAB listener block.  We're going to let Data Binding do the work
         *        of binding viewModel actions to the view and react to viewModel updates.
         *
         */
        findViewById<FloatingActionButton>(R.id.calculate_fab).setOnClickListener { _ ->

            /** TODO Lab 1: Everywhere inside of this action block replace the findViewById(..) lookup
             *         with a property lookup on the binding.
             *   Hint: Because these views are bound to the child binding / layout file included inside
             *         of content_tip_calculator.xml, you'll need to access the binding through
             *         binding.content...
             *         The content property comes from the id assigned to the include block
             *         in activity_tip_calculator.
             *             <include android:id="@+id/content"  <== This id generates a getter for the child binding.
             *                      layout="@layout/content_tip_calculator" />
             */
            // Without data binding, we have to manually set the inputs on our view model.
            calculatorViewModel.checkAmtInput = (findViewById<EditText>(R.id.input_check_amount)).text.toString()
            calculatorViewModel.tipPctInput = (findViewById<EditText>(R.id.input_tip_percentage)).text.toString()

            // Invoke Calculate Tip on the ViewModel
            calculatorViewModel.calculateTip()

            // After the calculation, we need to manually update our view elements
            calculatorViewModel.tipCalculation.let { tc ->
                (findViewById<TextView>(R.id.bill_amount)).text = getString(R.string.dollar_amount, tc.checkAmount)
                (findViewById<TextView>(R.id.tip_dollar_amount)).text = getString(R.string.dollar_amount, tc.tipAmount)
                (findViewById<TextView>(R.id.total_dollar_amount)).text = getString(R.string.dollar_amount, tc.grandTotal)
                (findViewById<TextView>(R.id.calculation_name)).text = tc.locationName
            }
        }


        /**
         * TODO Lab 2: Add a line below to assign calculatorVm to the view's vm variable.
         */
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
        /**
         * TODO Lab 2: (Optional) Update this line to access the CalculatorView model from
         *        binding.vm after the variable has been defined in the ActivityTipCalculatorBinding
         *        This goes along with the optional removal of the calculatorViewModel reference above.
         */
        calculatorViewModel.loadTipCalculation(tipCalc)

        /** TODO Lab 1: Replace this findViewById with the property that the binding gives you to access the root view. */
        Snackbar.make(window.decorView.findViewById(android.R.id.content), "Loaded ${tipCalc.locationName}", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSaveTip(name: String) {
        /**
         * TODO Lab 2: (Optional) Update this line to access the CalculatorView model from
         *        binding.vm after the variable has been defined in the ActivityTipCalculatorBinding
         *        This goes along with the optional removal of the calculatorViewModel reference above.
         */
        calculatorViewModel.saveCurrentTip(name)

        /** TODO Lab 1: Everywhere inside of this block replace the findViewById(..) lookup
         *         with a property lookup on the binding.
         *   Hint: Because these views are bound to the child binding / layout file included inside
         *         of content_tip_calculator.xml, you'll need to access the binding through
         *         binding.content...
         *         The content property comes from the id assigned to the include block
         *         in activity_tip_calculator.
         *             <include android:id="@+id/content"  <== This id generates a getter for the child binding.
         *                      layout="@layout/content_tip_calculator" />
         */
        /**
         * TODO Lab 2: Remove this entire block to update the view after saving the tip.  We're going to let
         *        data binding react to the changed ViewModel state.
         */
        calculatorViewModel.tipCalculation.let { tc ->
            (findViewById<TextView>(R.id.bill_amount)).text = getString(R.string.dollar_amount, tc.checkAmount)
            (findViewById<TextView>(R.id.tip_dollar_amount)).text = getString(R.string.dollar_amount, tc.tipAmount)
            (findViewById<TextView>(R.id.total_dollar_amount)).text = getString(R.string.dollar_amount, tc.grandTotal)
            (findViewById<TextView>(R.id.calculation_name)).text = tc.locationName
        }

        /** TODO Lab 1: Replace this findViewById with the property that the binding gives you to access the root view. */
        Snackbar.make(window.decorView.findViewById(android.R.id.content), "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

}

