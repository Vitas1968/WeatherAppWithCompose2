package com.dorofeev.weatherappwithcomposev2.utils

import com.dorofeev.weatherappwithcomposev2.data.WeatherData

sealed class LoadingStatus{
    data class Success(val data: WeatherData? = WeatherData()) : LoadingStatus()
    data class Failure(val exception: Throwable? = null) : LoadingStatus()
    data class Loading(val progress : Boolean? = null) : LoadingStatus()
}
