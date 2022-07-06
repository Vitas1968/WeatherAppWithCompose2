package com.dorofeev.weatherappwithcomposev2.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dorofeev.weatherappwithcomposev2.rest.interactors.WeatherInteractor

class MainViewModelFactory(val weatherInteractor: WeatherInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  modelClass.getConstructor(WeatherInteractor::class.java).newInstance(weatherInteractor)
    }
}