package com.dorofeev.weatherappwithcomposev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.dorofeev.weatherappwithcomposev2.data.WeatherData
import com.dorofeev.weatherappwithcomposev2.factories.ViewModelFactory
import com.dorofeev.weatherappwithcomposev2.screens.DetailScreen
import com.dorofeev.weatherappwithcomposev2.ui.theme.WeatherAppWithComposeV2Theme
import com.dorofeev.weatherappwithcomposev2.utils.getViewModel
import com.dorofeev.weatherappwithcomposev2.viewmodels.DetailViewModel
import javax.inject.Inject

class DetailActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        getDependencyViewModel()
        val weatherData = intent.extras?.getParcelable<WeatherData>("detailData")
        weatherData?.let {
            viewModel.updWeather = it
        }


        setContent {
            WeatherAppWithComposeV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailScreen(viewModel)
                }
            }
        }
    }

    private fun getDependencyViewModel() {
        viewModel = getViewModel(this, viewModelFactory, DetailViewModel::class.java)
    }

    private fun injectComponent() {
        (applicationContext as WeatherApp).component.inject(this)
    }
}

