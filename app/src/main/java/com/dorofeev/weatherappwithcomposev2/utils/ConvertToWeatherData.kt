package com.dorofeev.weatherappwithcomposev2.utils

import androidx.compose.runtime.State
import com.dorofeev.weatherappwithcomposev2.data.WeatherData

fun State<LoadingStatus>.convertToWeatherData() =
    if (value is LoadingStatus.Success) (value as LoadingStatus.Success).data else WeatherData()

fun State<LoadingStatus>.convertToLoading() =
    if (value is LoadingStatus.Loading) (value as LoadingStatus.Loading).progress else null

fun State<LoadingStatus>.convertToFailure() =
    if (value is LoadingStatus.Failure) (value as LoadingStatus.Failure).exception else null
