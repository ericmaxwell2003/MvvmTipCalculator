package com.acme.tipcalculator.di

import com.acme.tipcalculator.model.Calculator
import com.acme.tipcalculator.model.RestaurantCalculator
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun calculator() : Calculator {
        return RestaurantCalculator()
    }

}