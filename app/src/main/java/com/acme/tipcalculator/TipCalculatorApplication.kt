package com.acme.tipcalculator

import android.app.Application
import timber.log.Timber

class TipCalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}