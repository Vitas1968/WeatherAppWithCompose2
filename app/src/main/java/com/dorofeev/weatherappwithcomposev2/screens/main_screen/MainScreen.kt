package com.dorofeev.weatherappwithcomposev2.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.dorofeev.weatherappwithcomposev2.screens.main_screen.StartPageMainScreen
import com.dorofeev.weatherappwithcomposev2.viewmodels.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val sourceState = viewModel.statusStateFlow.collectAsState()
    val state = remember { sourceState }
    StartPageMainScreen(state = state, viewModel = viewModel)
}


