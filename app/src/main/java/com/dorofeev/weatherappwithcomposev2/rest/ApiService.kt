package com.dorofeev.weatherappwithcomposev2.rest

import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // val url = "https://api.weatherapi.com/v1/current.json?key=faf1b94436954656bfb114352222706&q=London&aqi=no"

    @GET("current.json")
    fun getWeather(
        @Query("key") apiKey:String = "faf1b94436954656bfb114352222706",
        @Query("q") city:String,
        @Query("aqi") aqi:String = "no",
    ): Deferred<WeatherDTOClass>
}