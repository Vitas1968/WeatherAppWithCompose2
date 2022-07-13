package com.dorofeev.weatherappwithcomposev2.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dorofeev.weatherappwithcomposev2.R.drawable
import com.dorofeev.weatherappwithcomposev2.R.string

@Composable
fun BackgroundMainScreen() {
    Image(
        painter = painterResource(id = drawable.main_background_img),
        contentDescription = stringResource(id = string.main_background_description),
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds
    )
}