package com.dorofeev.weatherappwithcomposev2.screens.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(widthDp = 400, heightDp = 700)
@Composable
fun InputCityItem(){
    var message by remember{mutableStateOf("")}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            //.fillMaxSize()
            .alpha(0.5f),
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { enterValue ->
                message = enterValue
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 19.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor= Color.Green, // цвет при получении фокуса
                unfocusedBorderColor = Color.Yellow,
                textColor = Color.Black
            ),
            label = { Text(text = "City name")}
        )

    }
}