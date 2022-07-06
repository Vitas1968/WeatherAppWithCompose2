package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import javax.inject.Inject

class DetailViewModel @Inject constructor():ViewModel() {

    private val initValue = WeatherData()

    val stateWeatherScreen : MutableState<WeatherData> = mutableStateOf(initValue)

    var updWeather:WeatherData = initValue
        set(value) {
            stateWeatherScreen.value = value
            field = value
        }
}