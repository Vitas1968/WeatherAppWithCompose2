package com.dorofeev.weatherappwithcomposev2.rest.repository

import com.dorofeev.weatherappwithcomposev2.rest.ApiService
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
) : IWeatherRepository {

    suspend fun getWeather(city: String): WeatherDTOClass {
        return apiService.getWeather(city = city).await()
    }
}