package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.rest.interactors.IWeatherInteractor
import com.dorofeev.weatherappwithcomposev2.rest.interactors.WeatherInteractor
import com.dorofeev.weatherappwithcomposev2.utils.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.utils.createDefaultExceptionHandlerNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    private val weatherInteractor: IWeatherInteractor
) : ViewModel() {

    private var weatherJob: Job? = null
    private val dispatcherIO = Dispatchers.IO

    private val weatherDataStateFlow: MutableStateFlow<LoadingStatus> =
        MutableStateFlow(LoadingStatus.Success<WeatherData>(WeatherData()))

    val weatherStateFlow: StateFlow<LoadingStatus> = weatherDataStateFlow

    val stateWeatherScreen: MutableState<WeatherData> = mutableStateOf(WeatherData())

    //private val weatherLiveDate_ = MutableLiveData<WeatherData>()
    var weatherLiveDate: LiveData<WeatherData> = MutableLiveData()
    private val coroutineContext: CoroutineContext =
        dispatcherIO + createDefaultExceptionHandlerNew(weatherDataStateFlow)

    /*
        fun updateWeatherData(city: String, stateMapOfWeather: WeatherData) {
            weatherInteractor.requestWeatherData(city, stateMapOfWeather)
            //getWeatherLiveDate().observeAsState()
        }

        fun updateWeatherData2(city: String, stateMapOfWeather: MutableState<WeatherData>) {
            weatherInteractor.requestWeatherData2(city, stateMapOfWeather)
        }

         fun updateWeatherData3(city: String) {
             weatherLiveDate = weatherInteractor.requestWeatherData3(city)
         }

        fun updateWeatherData4(city: String) {
            weatherInteractor.requestWeatherData3(city).observeForever {
                stateWeatherScreen.value = it
            }
        }
    */
    fun getWeather(city: String) {
        if (weatherJob?.isActive == true) return
        weatherJob = viewModelScope.launch(coroutineContext) {
            (weatherInteractor as WeatherInteractor)
                .getWeather(city)
                ?.let { weatherData ->
                    weatherDataStateFlow.value = LoadingStatus.Success(weatherData)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherJob?.cancel()
    }
}