package com.dorofeev.weatherappwithcomposev2.rest.interactors

import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.rest.repository.IWeatherRepository
import com.dorofeev.weatherappwithcomposev2.rest.repository.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor (
    private val repository : IWeatherRepository
): IWeatherInteractor {


    suspend fun getWeather(city: String) : WeatherData? {
        val dtoObject = (repository as WeatherRepository).getWeather(city)
return convertToWeatherData(dtoObject)
    }

    private fun convertToWeatherData1(dtoClass:WeatherDTOClass?) =
        with(dtoClass){
            WeatherData(
                temp = this?.current?.temp_c.toString(),
                dateTime =  this?.location?.localtime,
                iconUrl = this?.current?.condition?.icon,
                city = this?.location?.name,
                windSpeed = this?.current?.wind_kph.toString(),
                windDir = this?.current?.wind_dir,
                humidity = this?.current?.humidity.toString()
            )
        }

    private fun convertToWeatherData(dtoClass:WeatherDTOClass?) =
        dtoClass?.run{
            WeatherData(
                temp = current?.temp_c.toString(),
                dateTime =  location?.localtime,
                iconUrl = current?.condition?.icon,
                city = location?.name,
                windSpeed = current?.wind_kph.toString(),
                windDir = current?.wind_dir,
                humidity = current?.humidity.toString()
            )
        }
}
