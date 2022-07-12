package com.dorofeev.weatherappwithcomposev2.rest.dto

import com.google.gson.annotations.SerializedName

data class ErrorWeatherDTOClass(
    @SerializedName("error") val error: Error? = null
) {

    data class Error(
        @SerializedName("code") val code: Int? = null, // 1006
        @SerializedName("message") val message: String? = null // No matching location found.
    )
}