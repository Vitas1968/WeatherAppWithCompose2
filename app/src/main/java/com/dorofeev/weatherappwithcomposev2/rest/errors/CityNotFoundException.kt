package com.dorofeev.weatherappwithcomposev2.rest.errors

class CityNotFoundException(
    val code: Int? = null, // 1006
    override val message: String? = null // No matching location found.
) : Exception(message)