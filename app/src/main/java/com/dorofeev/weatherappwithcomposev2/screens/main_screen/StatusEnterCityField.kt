package com.dorofeev.weatherappwithcomposev2.screens.main_screen



sealed class StatusEnterCityField{
    data class EnterCity(var city: String? = "") : StatusEnterCityField()
    data class Error(val isError: Boolean? = false) : StatusEnterCityField()
}
