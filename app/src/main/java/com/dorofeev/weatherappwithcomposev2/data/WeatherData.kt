package com.dorofeev.weatherappwithcomposev2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(
    var temp: String? = "no temp",
    var dateTime: String? = "no DateTime",
    var iconUrl: String? = "//cdn.weatherapi.com/weather/64x64/night/116.png",
    var city: String? = "no city",
    var windSpeed: String? = "no speed",
    var windDir: String? = "no dir",
    var humidity: String? = "no hum",
    var country: String? = "no country", // Russia
    var lat: String? = "no lat", // 56.01
    var lon: String? = "no lon", // 92.79
    var region: String? = "no region", // Krasnoyarsk
    var tz_id: String? = "no location" // Asia/Krasnoyarsk
) : Parcelable, IWeatherData


