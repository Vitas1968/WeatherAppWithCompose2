package com.dorofeev.weatherappwithcomposev2.utils

import androidx.compose.runtime.State
import com.dorofeev.weatherappwithcomposev2.screens.main_screen.StatusEnterCityField

fun State<StatusEnterCityField>.convertToEnterCityField() =
    if (value is StatusEnterCityField.EnterCity) (value as StatusEnterCityField.EnterCity).city else ""

fun State<StatusEnterCityField>.convertToErrorCityField() =
    if (value is StatusEnterCityField.Error) (value as StatusEnterCityField.Error).isError else false