package com.acme.tipcalculator.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.acme.tipcalculator.view.TipCalculatorActivity;
import com.acme.tipcalculator.viewmodel.CalculatorViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @ContributesAndroidInjector
    abstract TipCalculatorActivity tipCalculatorActivity();

    @Binds
    @IntoMap
    @ViewModelKey(CalculatorViewModel.class)
    abstract ViewModel bindViewModel(CalculatorViewModel viewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
