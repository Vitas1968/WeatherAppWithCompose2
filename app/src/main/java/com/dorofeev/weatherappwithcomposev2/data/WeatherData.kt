package com.dorofeev.weatherappwithcomposev2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(
    var temp: String? = "no temp" ,
    var dateTime: String? = "no DateTime",
    var iconUrl: String? ="//cdn.weatherapi.com/weather/64x64/night/116.png",
    var city: String? = "no city",
    var windSpeed: String? = "no speed",
    var windDir: String? = "no dir",
    var humidity: String? = "no hum"
) : Parcelable,IWeatherData


