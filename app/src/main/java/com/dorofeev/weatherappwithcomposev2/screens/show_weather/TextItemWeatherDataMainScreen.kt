package com.dorofeev.weatherappwithcomposev2.screens.show_weather

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.utils.convertToWeatherData

@Composable
fun TextItemWeatherDataMainScreen(
    state: State<LoadingStatus>,
    isShowDetailState: MutableState<Boolean>
) {
    val sourceWeatherParamsMap = with(state.convertToWeatherData()) {
        mapOf(
            "City" to this?.city,
            "Temp" to this?.temp + " C",
            "Wind" to "${this?.windSpeed}  km/h, dir: ${this?.windDir}",
            "Humidity" to this?.humidity + " %",
            "Country" to this?.country,
            "Latitude" to this?.lat,
            "Longitude" to this?.lon,
            "Region" to this?.region,
            "Location" to this?.tz_id
        )
    }

    CreateAndShowItems(sourceWeatherParamsMap, isShowDetailState)
}

@Composable
private fun CreateAndShowItems(
    weatherParamsMap: Map<String, String?>,
    isShowDetailState: MutableState<Boolean>
) {
    var textLocal = ""
    for (item in weatherParamsMap) {
        if (!isShowDetailState.value && item.key == "Country") break
        textLocal = "${item.key}: ${item.value}"
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = textLocal,//"20 Jun 2022 13:00",
            style = TextStyle(fontSize = 18.sp),
            color = Color.White
        )
    }
}

