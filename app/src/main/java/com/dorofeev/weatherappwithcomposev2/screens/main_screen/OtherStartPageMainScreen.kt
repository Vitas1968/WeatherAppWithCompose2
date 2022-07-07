package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import android.content.Intent
import android.util.Log
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
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dorofeev.weatherappwithcomposev2.DetailActivity
import com.dorofeev.weatherappwithcomposev2.R.drawable
import com.dorofeev.weatherappwithcomposev2.R.string
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
import com.dorofeev.weatherappwithcomposev2.ui.theme.ErrorColor
import com.dorofeev.weatherappwithcomposev2.utils.addHttpsToRequest
import com.dorofeev.weatherappwithcomposev2.utils.convertToFailure
import com.dorofeev.weatherappwithcomposev2.utils.convertToLoading
import com.dorofeev.weatherappwithcomposev2.utils.convertToWeatherData
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

@Composable
 fun OtherStartPageMainScreen(
    state: State<LoadingStatus>,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "To detail") },
                onClick = {
                    val detailIntent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("detailData", state.convertToWeatherData())
                    }
                    context.startActivity(detailIntent)
                    Log.d("MyTag", "FAB clicked")
                },

                backgroundColor = Color.Green,
                contentColor = Color.White
            )
        }
    ) {
        Image(
            painter = painterResource(id = drawable.main_background_img),
            contentDescription = stringResource(id = string.main_background_description),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
            contentScale = ContentScale.FillBounds
        )

        state.convertToLoading()?.let {
            if (it) ShowProgressbar()
        }

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
                                .size(40.dp)
                                .padding(end = 6.dp)
                        )
                    }

                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "City: ${state.convertToWeatherData()?.city}",//"20 Jun 2022 13:00",
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "Temp: ${state.convertToWeatherData()?.temp} C",//"20 Jun 2022 13:00",
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Wind: speed ${state.convertToWeatherData()?.windSpeed} km/h, dir: ${state.convertToWeatherData()?.windDir}",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Humidity: ${state.convertToWeatherData()?.humidity} %",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    state.convertToFailure()?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = "Error : ${state.convertToFailure()?.message}",
                            style = TextStyle(fontSize = 16.sp),
                            color = ErrorColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            ListButtonCity(viewModel)
        }
    }
}