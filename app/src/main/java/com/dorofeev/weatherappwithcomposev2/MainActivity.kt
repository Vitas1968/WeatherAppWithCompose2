package com.dorofeev.weatherappwithcomposev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dorofeev.weatherappwithcomposev2.factories.ViewModelFactory
import com.dorofeev.weatherappwithcomposev2.screens.MainScreen
import com.dorofeev.weatherappwithcomposev2.ui.theme.WeatherAppWithComposeV2Theme
import com.dorofeev.weatherappwithcomposev2.utils.getViewModel
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    //private val providerWeatherData = ProviderWeatherData(this)
    //private val weatherInteractor = WeatherInteractor(providerWeatherData)
   // private val factory = MainViewModelFactory(weatherInteractor)
   // private val viewModel: MainViewModel by viewModels { factory }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        getDependencyViewModel()
        setContent {
            WeatherAppWithComposeV2Theme {
                MainScreen(viewModel)
            }
        }
    }

    private fun getDependencyViewModel() {
        viewModel =
            getViewModel(this, viewModelFactory, MainViewModel::class.java)
    }

    private fun injectComponent() {
        (applicationContext as WeatherApp).component.inject(this)
    }

}

