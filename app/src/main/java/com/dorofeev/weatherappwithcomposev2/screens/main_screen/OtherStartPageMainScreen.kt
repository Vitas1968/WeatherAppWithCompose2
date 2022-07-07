package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dorofeev.weatherappwithcomposev2.DetailActivity
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.ui.theme.BlueLight
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
        // фон экрана
        BackgroundMainScreen()
        // отображаем или нет прогрессбар
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
                ContentCardMainScreen(state)
            }
            Spacer(modifier = Modifier.height(10.dp))
            ListButtonCity(viewModel)
        }
    }
}




