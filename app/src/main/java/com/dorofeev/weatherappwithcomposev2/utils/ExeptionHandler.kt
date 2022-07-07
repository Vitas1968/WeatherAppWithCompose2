package com.dorofeev.weatherappwithcomposev2.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.dorofeev.weatherappwithcomposev2.data.LoadingStatus
import com.dorofeev.weatherappwithcomposev2.factories.ViewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow

fun ViewModel.createDefaultExceptionHandlerNew(status: MutableStateFlow<LoadingStatus>): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, throwable ->
        status.tryEmit(LoadingStatus.Failure(throwable))
    }
}

fun <T : ViewModel> getViewModel(owner: ViewModelStoreOwner,factory: ViewModelFactory, aClass: Class<T>): T {
    return ViewModelProvider(owner, factory)[aClass]
}