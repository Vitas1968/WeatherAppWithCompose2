package com.dorofeev.weatherappwithcomposev2.di.modules

import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        RestModule::class,
        ViewModelModule::class,
    ]
)
open class AppModule