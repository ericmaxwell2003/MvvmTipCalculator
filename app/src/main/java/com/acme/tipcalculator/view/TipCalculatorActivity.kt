package com.acme.tipcalculator.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.acme.tipcalculator.R

class TipCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tip_calculator)

        setSupportActionBar(findViewById(R.id.toolbar))

    }

}

