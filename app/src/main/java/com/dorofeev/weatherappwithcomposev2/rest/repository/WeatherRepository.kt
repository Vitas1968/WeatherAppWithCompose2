package com.dorofeev.weatherappwithcomposev2.rest.repository

import com.dorofeev.weatherappwithcomposev2.rest.ApiService
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.utils.checkResultSuccessOrThrowExceptions
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
) : IWeatherRepository {

    suspend fun getWeather(city: String): WeatherDTOClass? {
        val response = apiService.getWeatherAsync(city = city).await()
        checkResultSuccessOrThrowExceptions(response.code())
        return response.body()
    }
}