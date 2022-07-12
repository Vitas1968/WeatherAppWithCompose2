package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.lifecycle.ViewModel
import com.dorofeev.weatherappwithcomposev2.screens.main_screen.StatusEnterCityField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val mutableStatusCityFieldFlow: MutableStateFlow<StatusEnterCityField> =
        MutableStateFlow(StatusEnterCityField.EnterCity())

    val statusCityFieldFlow: StateFlow<StatusEnterCityField> = mutableStatusCityFieldFlow

}