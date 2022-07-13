package com.dorofeev.weatherappwithcomposev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dorofeev.weatherappwithcomposev2.screens.main.StartPageMainScreen
import com.dorofeev.weatherappwithcomposev2.ui.theme.WeatherAppWithComposeV2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppWithComposeV2Theme {
                StartPageMainScreen()
            }
        }
    }
}

