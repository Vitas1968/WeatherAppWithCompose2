package com.dorofeev.weatherappwithcomposev2.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
   val errorStatus: MutableState<Boolean> = mutableStateOf(false)
   val cityStatus: MutableState<String> = mutableStateOf("")
}