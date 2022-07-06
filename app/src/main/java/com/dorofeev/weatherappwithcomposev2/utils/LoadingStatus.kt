package com.dorofeev.weatherappwithcomposev2.utils

sealed class LoadingStatus{
    data class Success<T>(val data: T? = null) : LoadingStatus()
    data class Failure(val exception: Throwable) : LoadingStatus()
    data class Loading<T>(val progress : T? = null) : LoadingStatus()
}
