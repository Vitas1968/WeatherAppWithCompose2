package com.dorofeev.weatherappwithcomposev2.rest.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.utils.addHttpsToRequest
import org.json.JSONObject

class ProviderWeatherData(
    private val context: Context
) {
    private val weatherLiveDate = MutableLiveData<JSONObject>()

    private val API_KEY = "faf1b94436954656bfb114352222706"

    private val queue: RequestQueue
        get() = Volley.newRequestQueue(context)

    fun requestWeatherData(city: String, stateMapOfWeather: WeatherData) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&" +
                "q=$city" +
                "&aqi=no"
        // val url = "https://api.weatherapi.com/v1/current.json?key=faf1b94436954656bfb114352222706&q=London&aqi=no"

        val stringRequest = StringRequest(
            Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)

                val dateTime = obj.getJSONObject("location").getString("localtime")

                val urlIcon =
                    obj.getJSONObject("current").getJSONObject("condition").getString("icon").addHttpsToRequest()

                val temp = obj.getJSONObject("current").getString("temp_c")

                val cityLocal = obj.getJSONObject("location").getString("name")

                stateMapOfWeather.temp = temp
                stateMapOfWeather.city = cityLocal
                stateMapOfWeather.iconUrl = urlIcon
                stateMapOfWeather.dateTime = dateTime
            },
            { error ->
                Log.d("MyLog", "Volley error: $error")
            }
        )
        queue.add(stringRequest)
    }

    fun requestWeatherData2(city: String, stateMapOfWeather: MutableState<WeatherData>) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&" +
                "q=$city" +
                "&aqi=no"

        val stringRequest = StringRequest(
            Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)

                val dateTime = obj.getJSONObject("location").getString("localtime")

                val urlIcon =
                    obj.getJSONObject("current").getJSONObject("condition").getString("icon").addHttpsToRequest()

                val temp = obj.getJSONObject("current").getString("temp_c")

                val cityLocal = obj.getJSONObject("location").getString("name")
                val windSpeed = obj.getJSONObject("current").getString("wind_kph")
                val windDir = obj.getJSONObject("current").getString("wind_dir")
                val humidity = obj.getJSONObject("current").getString("humidity")

                stateMapOfWeather.value =
                    createWeatherDataClass(temp, dateTime, urlIcon, cityLocal, windSpeed, windDir, humidity)

            },
            { error ->
                Log.d("MyLog", "Volley error: $error")
            }
        )
        queue.add(stringRequest)
    }

    fun requestWeatherData3(city: String) :  MutableLiveData<JSONObject>{
         val weatherLiveDate = MutableLiveData<JSONObject>()
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&" +
                "q=$city" +
                "&aqi=no"

        val stringRequest = StringRequest(
            Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                weatherLiveDate.postValue(obj)
            },
            { error ->
                Log.d("MyLog", "Volley error: $error")
            }
        )
        queue.add(stringRequest)
        return  weatherLiveDate
    }

    private fun createWeatherDataClass(
        temp: String,
        dateTime: String,
        iconUrl: String,
        city: String,
        windSpeed: String,
        windDir: String,
        humidity: String
    ) = WeatherData(
        temp = temp,
        dateTime = dateTime,
        iconUrl = iconUrl,
        city = city,
        windSpeed = windSpeed,
        windDir = windDir,
        humidity = humidity
    )
}