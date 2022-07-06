package com.dorofeev.weatherappwithcomposev2.utils

import java.io.IOException

fun checkResultSuccessOrThrowExceptions(operationResult: Int) {
    if (operationResult != 200) throw IOException(Errors.BAD_REQUEST)
}