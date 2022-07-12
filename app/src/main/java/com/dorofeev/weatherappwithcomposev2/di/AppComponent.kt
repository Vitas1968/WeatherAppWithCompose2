package com.dorofeev.weatherappwithcomposev2.di

import android.content.Context
import com.dorofeev.weatherappwithcomposev2.DetailActivity
import com.dorofeev.weatherappwithcomposev2.MainActivity
import com.dorofeev.weatherappwithcomposev2.ShowWeatherActivity
import com.dorofeev.weatherappwithcomposev2.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
    fun inject(showWeatherActivity: ShowWeatherActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        @BindsInstance
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}