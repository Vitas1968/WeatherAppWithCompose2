package com.dorofeev.weatherappwithcomposev2.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorofeev.weatherappwithcomposev2.R.drawable
import com.dorofeev.weatherappwithcomposev2.R.string

//@Preview(widthDp = 400, heightDp = 700)
@Composable
fun InputCityItem(
    cityState: MutableState<String>,
    errorState: MutableState<Boolean>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .alpha(0.5f),
    ) {
        OutlinedTextField(
            value = cityState.value,
            onValueChange = { enterValue ->
                cityState.value = enterValue

            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 19.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Green, // цвет рамки при получении фокуса
                unfocusedBorderColor = Color.Yellow,
                textColor = Color.Black
            ),
            label = { Text(text = "City name") },
            trailingIcon = { if (errorState.value) setErrorImage() }
        )

    }
}

@Composable
private fun setErrorImage() {
    Image(
        painter = painterResource(id = drawable.ic_baseline_error_24),
        contentDescription = stringResource(id = string.error_field_city_description),
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
//                        .alpha(0.5f),
//                    contentScale = ContentScale.FillBounds
    )
}