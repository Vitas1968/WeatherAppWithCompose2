package com.dorofeev.weatherappwithcomposev2.screens.main_screen

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
import androidx.compose.runtime.State
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
   // stateErrorCityField: MutableState<Boolean>,
   // mutableStatusCityFieldFlow : MutableStateFlow<String>,
    cityFieldState : State<StatusEnterCityField>
){

// val vm = viewModel(DetailViewModel::class.java)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .alpha(0.5f),
    ) {
        OutlinedTextField(
            value = cityFieldState.value ,
            onValueChange = { enterValue ->
                (cityFieldState.value as StatusEnterCityField.EnterCity).city = enterValue
            },
            //isError = stateErrorCityField.value,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 19.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor= Color.Green, // цвет при получении фокуса
                unfocusedBorderColor = Color.Yellow,
                textColor = Color.Black
            ),
            label = { Text(text = "City name")},
            trailingIcon = {
               if (stateErrorCityField.value) setErrorImage()
            }
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