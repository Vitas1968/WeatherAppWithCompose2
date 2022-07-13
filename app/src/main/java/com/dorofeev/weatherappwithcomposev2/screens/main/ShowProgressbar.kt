package com.dorofeev.weatherappwithcomposev2.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dorofeev.weatherappwithcomposev2.utils.Constants.DEFAULT_MDFR_SHOW_PRGRS_BAR

@Composable
fun ShowProgressbar(modifier: Modifier? = null) {
    CircularProgressIndicator(modifier = modifier ?: DEFAULT_MDFR_SHOW_PRGRS_BAR)
}