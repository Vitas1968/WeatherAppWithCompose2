package com.dorofeev.weatherappwithcomposev2.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dorofeev.weatherappwithcomposev2.data.WeatherData

class DetailViewModelFactory(private val weatherData: WeatherData) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(WeatherData::class.java).newInstance(weatherData)
    }
}