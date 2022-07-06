package com.dorofeev.weatherappwithcomposev2

import android.app.Application
import com.dorofeev.weatherappwithcomposev2.di.AppComponent
import com.dorofeev.weatherappwithcomposev2.di.DaggerAppComponent
import com.dorofeev.weatherappwithcomposev2.di.modules.AppModule

class WeatherApp:Application() {


    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule())
            .applicationContext(applicationContext)
            .build()
    }
}
