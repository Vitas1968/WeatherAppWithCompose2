package com.dorofeev.weatherappwithcomposev2.rest.interactors

import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.rest.repository.IWeatherRepository
import com.dorofeev.weatherappwithcomposev2.rest.repository.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val repository: IWeatherRepository
) : IWeatherInteractor {

    suspend fun getWeather(city: String): WeatherData? {
        val dtoObject = (repository as WeatherRepository).getWeather(city)
        return convertToWeatherData(dtoObject)
    }

    private fun convertToWeatherData(dtoClass: WeatherDTOClass?) =
        dtoClass?.run {
            WeatherData(
                temp = current?.temp_c.toString(),
                dateTime = location?.localtime,
                iconUrl = current?.condition?.icon,
                city = location?.name,
                windSpeed = current?.wind_kph.toString(),
                windDir = current?.wind_dir,
                humidity = current?.humidity.toString(),
                country = location?.country,
                lat = location?.lat.toString(), // 56.01
                lon = location?.lon.toString(), // 92.79
                region = location?.region, // Krasnoyarsk
                tz_id = location?.tz_id
            )
        }
}
