package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.rest.interactors.IWeatherInteractor
import com.dorofeev.weatherappwithcomposev2.rest.interactors.WeatherInteractor
import com.dorofeev.weatherappwithcomposev2.utils.createDefaultExceptionHandlerNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private val _statusStateFlow: MutableStateFlow<LoadingStatus> =
        MutableStateFlow(LoadingStatus.Success(WeatherData()))

    val statusStateFlow: StateFlow<LoadingStatus> = _statusStateFlow

    private val coroutineContext: CoroutineContext =
        dispatcherIO + createDefaultExceptionHandlerNew(_statusStateFlow)

    fun getWeather(city: String) {
        if (weatherJob?.isActive == true) return
        _statusStateFlow.value = LoadingStatus.Loading(true)
        weatherJob = viewModelScope.launch(coroutineContext) {
            (weatherInteractor as WeatherInteractor)
                .getWeather(city)
                ?.let { weatherData ->
                    delay(1000)
                    _statusStateFlow.value = LoadingStatus.Loading(false)
                    _statusStateFlow.value = LoadingStatus.Success(weatherData)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherJob?.cancel()
    }
}