package com.dorofeev.weatherappwithcomposev2.screens.main_screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dorofeev.weatherappwithcomposev2.DetailActivity
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

@Composable
fun StartPageMainScreen(
    viewModel: MainViewModel
) {
    val cityFieldFlowSource = viewModel.statusCityFieldFlow//.collectAsState()
    val stateEnterCityField = remember { mutableStateOf(cityFieldFlowSource.value) }
    //val stateEnterCityField = remember { cityFieldFlowSource }
    val stateIsErrorCityField = remember { mutableStateOf(false) }
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
                stateErrorCityField = stateIsErrorCityField,
                mutableStatusCityFieldFlow = viewModel.mutableStatusCityFieldFlow,
                cityFieldState = stateEnterCityField
            )
        }
        Button(onClick = {
            if (stateEnterCityField.value.isNotBlank()) {
                stateIsErrorCityField.value = false
                val detailIntent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("city", stateEnterCityField.value)
                }
                context.startActivity(detailIntent)
            } else stateIsErrorCityField.value = true

        }) {
            Text(
                text = "Get weather",
                color = Color.White
            )
        }
    }
}




