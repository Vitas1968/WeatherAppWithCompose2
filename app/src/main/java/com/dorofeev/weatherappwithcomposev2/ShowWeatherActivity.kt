package com.dorofeev.weatherappwithcomposev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dorofeev.weatherappwithcomposev2.factories.ViewModelFactory
import com.dorofeev.weatherappwithcomposev2.rest.errors.getViewModel
import com.dorofeev.weatherappwithcomposev2.screens.show_weather.ShowWeather
import com.dorofeev.weatherappwithcomposev2.ui.theme.WeatherAppWithComposeV2Theme
import com.dorofeev.weatherappwithcomposev2.viewmodels.ShowWeatherViewModel
import javax.inject.Inject

class ShowWeatherActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ShowWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        getDependencyViewModel()
        val weatherData = intent.extras?.getString("city")
        weatherData?.let {
            viewModel.city = it
        }
        setContent {
            WeatherAppWithComposeV2Theme {
                ShowWeather(viewModel)
            }
        }

    }
    private fun getDependencyViewModel() {
        viewModel =
            getViewModel(this, viewModelFactory, ShowWeatherViewModel::class.java)
    }

    private fun injectComponent() {
        (applicationContext as WeatherApp).component.inject(this)
    }
}

