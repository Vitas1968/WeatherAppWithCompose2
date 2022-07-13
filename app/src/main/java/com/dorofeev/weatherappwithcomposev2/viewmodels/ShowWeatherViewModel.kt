package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus.Loading
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus.Success
import com.dorofeev.weatherappwithcomposev2.rest.errors.createDefaultExceptionHandlerNew
import com.dorofeev.weatherappwithcomposev2.rest.interactors.IWeatherInteractor
import com.dorofeev.weatherappwithcomposev2.rest.interactors.WeatherInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ShowWeatherViewModel@Inject constructor(
    private val weatherInteractor: IWeatherInteractor
) : ViewModel() {

    private var weatherJob: Job? = null
    private val dispatcherIO = Dispatchers.IO
    var city = ""

    private val _statusStateFlow: MutableStateFlow<LoadingStatus> =
        MutableStateFlow(Success())
    val isShowDetailState = mutableStateOf(false)


    val statusStateFlow: StateFlow<LoadingStatus> = _statusStateFlow

    private val coroutineContext: CoroutineContext =
        dispatcherIO + createDefaultExceptionHandlerNew(_statusStateFlow)

    fun getWeather(city: String) {
        if (weatherJob?.isActive == true) return
        _statusStateFlow.value = Loading(true)
        weatherJob = viewModelScope.launch(coroutineContext) {
            (weatherInteractor as WeatherInteractor)
                .getWeather(city)
                ?.let { weatherData ->
                    delay(1000)
                    _statusStateFlow.value = Loading(false)
                    _statusStateFlow.value = Success(weatherData)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherJob?.cancel()
    }
}