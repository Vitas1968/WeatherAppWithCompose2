package com.dorofeev.weatherappwithcomposev2.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dorofeev.weatherappwithcomposev2.DetailActivity
import com.dorofeev.weatherappwithcomposev2.R
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
import com.dorofeev.weatherappwithcomposev2.utils.addHttpsToRequest
import com.dorofeev.weatherappwithcomposev2.utils.convertToWeatherData
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

//@Preview(showBackground = true)
@Composable
fun MainScreen(viewModel: MainViewModel) {

    //храним состояние во вью модели

    val mapWeatherState = viewModel.weatherStateFlow.collectAsState()
    //val stateFromViewModel = remember{viewModel.stateWeatherScreen}
    val stateWeather = remember{mapWeatherState}
    val context = LocalContext.current


    // получаем данные в ливдате вьюмодели и тут преобразуем её в state
    //val stateWithLiveDate = viewModel.weatherLiveDate.observeAsState(WeatherData()) as MutableState

    //этот state сохраняет данные автоматически при смене ориентации экрана например
     //val stateWeatherDataSaveable = rememberSaveable { mutableStateOf(WeatherData()) }

    // тоже так можно,другая форма записи вот этого stateWeatherData = remember { mutableStateOf(WeatherData()) }
    //но тут есть определенные тараканы
    //val stateWeatherDataAuxiliary by remember { mutableStateOf(WeatherData()) }

    // этот state не сохраняет данные автоматически при смене ориентации экрана например
    // и при вовороте экрана мы увидим дефолтные значения дата класса
    //val stateWeatherData = remember { mutableStateOf(WeatherData()) }


    // viewModel.updateWeatherData2("Krasnoyarsk", stateWeatherData)

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {Text(text = "To detail")},
                onClick = {
                    val detailIntent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("detailData",stateWeather.convertToWeatherData())
                    }
                    context.startActivity(detailIntent)
                    Log.d("MyTag", "FAB clicked" ) },
                icon = {Icon(Filled.Add, contentDescription = "Добавить")},
                backgroundColor = Color.Green,
                contentColor = Color.White
            )
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_background_img),
            contentDescription = stringResource(id = R.string.main_background_description),
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


                            stateWeather.convertToWeatherData()?.dateTime?.let { it1 ->
                                Text(
                                    modifier = Modifier.padding(start = 6.dp),
                                    text = it1,//"20 Jun 2022 13:00",
                                    style = TextStyle(fontSize = 16.sp),
                                    color = Color.White
                                )
                            }




                        AsyncImage(
                            model = stateWeather.convertToWeatherData()?.iconUrl?.addHttpsToRequest(),
                          //  model = stateFromViewModel.value.iconUrl,//"https://cdn.weatherapi.com/weather/64x64/day/116.png",
                            contentDescription = stringResource(id = R.string.icon_weather_description),
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 6.dp)
                        )
                    }

                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "City: ${stateWeather.convertToWeatherData()?.city}",//"20 Jun 2022 13:00",
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "Temp: ${stateWeather.convertToWeatherData()?.temp} C",//"20 Jun 2022 13:00",
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Wind: speed ${stateWeather.convertToWeatherData()?.windSpeed} km/h, dir: ${stateWeather.convertToWeatherData()?.windDir}",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Humidity: ${stateWeather.convertToWeatherData()?.humidity} %",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            ListButtonCity(viewModel)

        }
    }
}

@Composable
fun ListButtonCity(
    viewModel: MainViewModel
) {
    //как получить котекст внутри Composable фунции
    val context = LocalContext.current


    val cities = stringArrayResource(id = R.array.cities).toList()
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


