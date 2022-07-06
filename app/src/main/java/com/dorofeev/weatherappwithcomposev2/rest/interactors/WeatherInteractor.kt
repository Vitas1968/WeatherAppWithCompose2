package com.dorofeev.weatherappwithcomposev2.rest.interactors

import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.rest.dto.WeatherDTOClass
import com.dorofeev.weatherappwithcomposev2.rest.repository.IWeatherRepository
import com.dorofeev.weatherappwithcomposev2.rest.repository.WeatherRepository
import com.dorofeev.weatherappwithcomposev2.utils.addHttpsToRequest
import org.json.JSONObject
import javax.inject.Inject

class WeatherInteractor @Inject constructor (
    //private val providerWeatherData: ProviderWeatherData
    private val repository : IWeatherRepository
): IWeatherInteractor {
/*
    fun requestWeatherData(city: String, stateMapOfWeather: WeatherData) {
        providerWeatherData.requestWeatherData(city, stateMapOfWeather)
    }

    fun requestWeatherData2(city: String, stateMapOfWeather: MutableState<WeatherData>) {
        providerWeatherData.requestWeatherData2(city, stateMapOfWeather)
    }

    fun requestWeatherData3(city: String): MutableLiveData<WeatherData> {
        val weatherLiveDate = MutableLiveData<WeatherData>()
        providerWeatherData.requestWeatherData3(city).observeForever {
            weatherLiveDate.postValue(transformJSONObjectToWeatherData(it))
        }
        return weatherLiveDate
    }
*/

    suspend fun getWeather(city: String) : WeatherData? {
        val dtoObject = (repository as WeatherRepository).getWeather(city)
return convertToWeatherData(dtoObject)
    }

    private fun convertToWeatherData(dtoClass:WeatherDTOClass) =
        with(dtoClass){
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





    private fun transformJSONObjectToWeatherData(response: JSONObject): WeatherData {

        val dateTime = response.getJSONObject("location").getString("localtime")

        val urlIcon =
            response.getJSONObject("current").getJSONObject("condition").getString("icon").addHttpsToRequest()
        val temp = response.getJSONObject("current").getString("temp_c")
        val cityLocal = response.getJSONObject("location").getString("name")
        val windSpeed = response.getJSONObject("current").getString("wind_kph")
        val windDir = response.getJSONObject("current").getString("wind_dir")
        val humidity = response.getJSONObject("current").getString("humidity")
        return WeatherData(
            temp = temp,
            dateTime = dateTime,
            iconUrl = urlIcon,
            city = cityLocal,
            windSpeed = windSpeed,
            windDir = windDir,
            humidity = humidity
        )
    }
}
