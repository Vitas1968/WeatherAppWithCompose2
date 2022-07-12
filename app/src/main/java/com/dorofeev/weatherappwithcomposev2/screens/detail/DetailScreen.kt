package com.dorofeev.weatherappwithcomposev2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dorofeev.weatherappwithcomposev2.R.drawable
import com.dorofeev.weatherappwithcomposev2.R.string
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
import com.dorofeev.weatherappwithcomposev2.viewmodels.DetailViewModel

@Composable
 fun DetailScreen(viewModel: DetailViewModel){
    //храним состояние во вью модели
    val stateFromViewModel = remember{viewModel.stateWeatherScreen}

    Image(
        painter = painterResource(id = drawable.main_background_img),
        contentDescription = stringResource(id = string.main_background_description),
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds
    )
    Column(
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    stateFromViewModel.value.dateTime?.let {
                        Text(
                            modifier = Modifier.padding(start = 6.dp),
                            text = it,//"20 Jun 2022 13:00",
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.White
                        )
                    }



                    AsyncImage(
                        model = stateFromViewModel.value.iconUrl,//"https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = stringResource(id = string.icon_weather_description),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 6.dp)
                    )
                }

                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "City: ${stateFromViewModel.value.city}",//"20 Jun 2022 13:00",
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = "Temp: ${stateFromViewModel.value.temp} C",//"20 Jun 2022 13:00",
                    style = TextStyle(fontSize = 22.sp),
                    color = Color.White
                )

                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Wind: speed ${stateFromViewModel.value.windSpeed} km/h, dir: ${stateFromViewModel.value.windDir}",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Humidity: ${stateFromViewModel.value.humidity} %",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}