package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.ui.theme.ErrorColor
import com.dorofeev.weatherappwithcomposev2.utils.convertToFailure

@Composable
fun FailureTextItem(state: State<LoadingStatus>) {
    state.convertToFailure()?.let {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Error : ${state.convertToFailure()?.message}",
            style = TextStyle(fontSize = 16.sp),
            color = ErrorColor
        )
    }
}