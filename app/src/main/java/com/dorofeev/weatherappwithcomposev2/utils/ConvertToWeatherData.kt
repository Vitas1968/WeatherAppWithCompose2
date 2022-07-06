package com.dorofeev.weatherappwithcomposev2.utils

import androidx.compose.runtime.State
import com.dorofeev.weatherappwithcomposev2.data.WeatherData

fun State<LoadingStatus>.convertToWeatherData() =
    (value as LoadingStatus.Success<WeatherData>).data
