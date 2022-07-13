package com.dorofeev.weatherappwithcomposev2.screens.show_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dorofeev.weatherappwithcomposev2.R.string
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.screens.main.FailureTextItem
import com.dorofeev.weatherappwithcomposev2.utils.addHttpsToRequest
import com.dorofeev.weatherappwithcomposev2.utils.convertToWeatherData

@Composable
fun ContentCardMainScreen(
    state: State<LoadingStatus>,
    isShowDetailState: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            state.convertToWeatherData()?.dateTime?.let { it1 ->
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = it1,//"20 Jun 2022 13:00",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
            }

            AsyncImage(
                model = state.convertToWeatherData()?.iconUrl?.addHttpsToRequest(),
                contentDescription = stringResource(id = string.icon_weather_description),
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 6.dp)
            )
        }
        TextItemWeatherDataMainScreen(state, isShowDetailState)
        FailureTextItem(state)
    }
}