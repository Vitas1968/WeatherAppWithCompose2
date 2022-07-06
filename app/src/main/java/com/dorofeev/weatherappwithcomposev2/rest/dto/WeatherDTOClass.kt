package com.dorofeev.weatherappwithcomposev2.rest.dto

import com.google.gson.annotations.SerializedName

data class WeatherDTOClass(
    @SerializedName("current") val current: Current? = null,
    @SerializedName("location") val location: Location? = null
) {
    data class Current(
        @SerializedName("cloud") val cloud: Int? = null, // 50
        @SerializedName("condition")val condition: Condition? = null,
        @SerializedName("feelslike_c") val feelslike_c: Double? = null, // 19.0
        @SerializedName("feelslike_f") val feelslike_f: Double? = null, // 66.2
        @SerializedName("gust_kph") val gust_kph: Double? = null, // 2.9
        @SerializedName("gust_mph") val gust_mph: Double? = null, // 1.8
        @SerializedName("humidity") val humidity: Int? = null, // 68
        @SerializedName("is_day") val is_day: Int? = null, // 1
        @SerializedName("last_updated") val last_updated: String? = null, // 2022-07-01 14:15
        @SerializedName("last_updated_epoch") val last_updated_epoch: Int? = null, // 1656659700
        @SerializedName("precip_in") val precip_in: Double? = null, // 0.0
        @SerializedName("precip_mm") val precip_mm: Double? = null, // 0.0
        @SerializedName("pressure_in") val pressure_in: Double? = null, // 29.62
        @SerializedName("pressure_mb") val pressure_mb: Double? = null, // 1003.0
        @SerializedName("temp_c") val temp_c: Double? = null, // 19.0
        @SerializedName("temp_f") val temp_f: Double? = null, // 66.2
        @SerializedName("uv") val uv: Double? = null, // 6.0
        @SerializedName("vis_km") val vis_km: Double? = null, // 10.0
        @SerializedName("vis_miles") val vis_miles: Double? = null, // 6.0
        @SerializedName("wind_degree") val wind_degree: Int? = null, // 318
        @SerializedName("wind_dir") val wind_dir: String? = null, // NW
        @SerializedName("wind_kph") val wind_kph: Double? = null, // 3.6
        @SerializedName("wind_mph") val wind_mph: Double? = null // 2.2
    ) {
        data class Condition(
            @SerializedName("code") val code: Int? = null, // 1003
            @SerializedName("icon") val icon: String? = null, // //cdn.weatherapi.com/weather/64x64/day/116.png
            @SerializedName("text") val text: String? = null // Partly cloudy
        )
    }

    data class Location(
        @SerializedName("country") val country: String? = null, // Russia
        @SerializedName("lat") val lat: Double? = null, // 56.01
        @SerializedName("localtime") val localtime: String? = null, // 2022-07-01 14:22
        @SerializedName("localtime_epoch") val localtime_epoch: Long? = null, // 1656660165
        @SerializedName("lon") val lon: Double? = null, // 92.79
        @SerializedName("name") val name: String? = null, // Krasnoyarsk
        @SerializedName("region") val region: String? = null, // Krasnoyarsk
        @SerializedName("tz_id") val tz_id: String? = null // Asia/Krasnoyarsk
    )
}