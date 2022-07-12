package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.dorofeev.weatherappwithcomposev2.R.array
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

@Composable
fun ListButtonCity(
    viewModel: MainViewModel
) {
    val cities = stringArrayResource(id = array.cities).toList()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(start = 2.dp, end = 2.dp)
    ) {
        cities.forEach { city ->
            Button(
                onClick = { viewModel.getWeather(city) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .alpha(0.5f)
            ) {
                Text(text = city)
            }
        }

    }
}