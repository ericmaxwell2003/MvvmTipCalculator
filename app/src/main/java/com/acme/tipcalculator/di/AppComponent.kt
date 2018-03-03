package com.acme.tipcalculator.di

import android.app.Application
import com.acme.tipcalculator.TipCalculatorApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelModule::class, AppModule::class])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: TipCalculatorApplication)
}
