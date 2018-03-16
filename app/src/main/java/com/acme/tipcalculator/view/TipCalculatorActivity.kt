package com.acme.tipcalculator.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.acme.tipcalculator.R

class TipCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)
        setSupportActionBar(findViewById(R.id.toolbar))

        if(savedInstanceState != null) {
            Log.i("Foo", savedInstanceState.getString("Important", "cant find the value"))
        } else {
            Log.i("Foo", "Nada")
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("Important", "Imporant Value");
    }

}

