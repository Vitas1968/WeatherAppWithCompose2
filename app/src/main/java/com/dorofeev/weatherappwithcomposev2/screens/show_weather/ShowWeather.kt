package com.dorofeev.weatherappwithcomposev2.screens.show_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dorofeev.weatherappwithcomposev2.R.string
import com.dorofeev.weatherappwithcomposev2.screens.main.BackgroundMainScreen
import com.dorofeev.weatherappwithcomposev2.screens.main.ShowProgressbar
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
import com.dorofeev.weatherappwithcomposev2.ui.theme.FabColor
import com.dorofeev.weatherappwithcomposev2.utils.convertToLoading
import com.dorofeev.weatherappwithcomposev2.viewmodels.ShowWeatherViewModel

@Composable
fun ShowWeather(
    showWeatherViewModel: ShowWeatherViewModel
) {
    val sourceState = showWeatherViewModel.statusStateFlow.collectAsState()
    val loadingStatusState = remember { sourceState }
    val isShowDetailState = remember { showWeatherViewModel.isShowDetailState }
    val context = LocalContext.current
    showWeatherViewModel.getWeather(showWeatherViewModel.city)
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            var fabText = if (!isShowDetailState.value) stringResource(id =string.show_detail) else stringResource(id =string.hide_detail)
            var fabColor = if (!isShowDetailState.value) Color.Green else FabColor
            ExtendedFloatingActionButton(
                text = { Text(text = fabText) },
                onClick = {isShowDetailState.value = !isShowDetailState.value},
                backgroundColor = fabColor,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(12.dp)
            )
        }
    ) {
        // фон экрана
        BackgroundMainScreen()

        //  отображаем или нет прогрессбар
        loadingStatusState.convertToLoading()?.let {
            if (it) ShowProgressbar()
        }

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
                ContentCardMainScreen(loadingStatusState,isShowDetailState)
            }

            //Spacer(modifier = Modifier.height(10.dp))

        }
    }
}
