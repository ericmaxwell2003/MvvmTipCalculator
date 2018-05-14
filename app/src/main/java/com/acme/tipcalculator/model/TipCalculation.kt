package com.acme.tipcalculator.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TipCalculation(

        @PrimaryKey
        val locationName: String = "",

        val tipPct: Int = 0,
        val checkAmount: Double = 0.0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0)