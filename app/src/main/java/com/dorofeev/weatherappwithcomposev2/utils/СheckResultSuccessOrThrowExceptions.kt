package com.dorofeev.weatherappwithcomposev2.utils

import com.dorofeev.weatherappwithcomposev2.rest.dto.ErrorWeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.rest.errors.CityNotFoundException
import com.dorofeev.weatherappwithcomposev2.rest.errors.Errors
import com.google.gson.Gson
import okio.use
import retrofit2.Response
import java.io.IOException

fun checkResultSuccessOrThrowExceptions(response: Response<WeatherDTOClass>) {
    var cityNotFoundExc: ErrorWeatherDTOClass? = null
    if (response.errorBody() != null) {
        response.errorBody().use {
            val err = it?.string()
            cityNotFoundExc = Gson().fromJson(err, ErrorWeatherDTOClass::class.java)
        }

        cityNotFoundExc?.let {
            if (cityNotFoundExc!!.error?.code == 1006)
                throw CityNotFoundException(it.error?.code, "Город не найден")
            else
                throw IOException(Errors.BAD_REQUEST)
        }
    }
}
