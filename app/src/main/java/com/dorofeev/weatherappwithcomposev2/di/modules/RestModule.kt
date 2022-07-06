package com.dorofeev.weatherappwithcomposev2.di.modules

import com.dorofeev.weatherappwithcomposev2.rest.interactors.IWeatherInteractor
import com.dorofeev.weatherappwithcomposev2.rest.interactors.WeatherInteractor
import com.dorofeev.weatherappwithcomposev2.rest.repository.IWeatherRepository
import com.dorofeev.weatherappwithcomposev2.rest.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
interface RestModule {

    @Binds
    fun getRepository(repo: WeatherRepository) : IWeatherRepository

    @Binds
    fun getInteractor(repo: WeatherInteractor) : IWeatherInteractor

}