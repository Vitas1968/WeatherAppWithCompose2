package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorofeev.weatherappwithcomposev2.R.array
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.utils.convertToWeatherData

@Composable
fun TextItemWeatherDataMainScreen(state: State<LoadingStatus>) {
    val weatherParamsList = stringArrayResource(id = array.main_weather_param).toList()
    val weatherParamsMap = with(state.convertToWeatherData()){
        mapOf(
            "City" to this?.city,
            "Temp" to this?.temp,
            "winds" to this?.windSpeed,
            "windd" to this?.windDir,
            "Humidity" to this?.humidity
        )
    }

    weatherParamsList.forEach { params ->
        val textLocal = if (
            params.contains(
                "Wind",
                true
            )
        ) "$params: ${weatherParamsMap["winds"]} km/h, dir: ${weatherParamsMap["windd"]} "
        else "$params: ${weatherParamsMap[params]}"
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = textLocal,//"20 Jun 2022 13:00",
            style = TextStyle(fontSize = 18.sp),
            color = Color.White
        )
    }
}

