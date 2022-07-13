package com.dorofeev.weatherappwithcomposev2.screens.main

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dorofeev.weatherappwithcomposev2.ShowWeatherActivity
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight

import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

@Composable
fun StartPageMainScreen() {
    val viewModel = viewModel(MainViewModel::class.java)
    val stateCity = remember { viewModel.cityStatus }
    val stateError = remember { viewModel.errorStatus }
    val context = LocalContext.current

    // фон экрана
    BackgroundMainScreen()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            InputCityItem(
                cityState = stateCity,
                errorState = stateError
            )
        }
        Button(
            onClick = {
                if (stateCity.value.isNotBlank()) {
                    stateError.value = false
                    val detailIntent = Intent(context, ShowWeatherActivity::class.java).apply {
                        putExtra("city", stateCity.value)
                    }
                    context.startActivity(detailIntent)
                } else stateError.value = true
            }
        ) {
            Text(
                text = "Get weather",
                color = Color.White
            )
        }
    }
}




