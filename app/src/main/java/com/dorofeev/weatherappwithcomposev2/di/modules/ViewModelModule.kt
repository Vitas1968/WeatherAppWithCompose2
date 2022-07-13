package com.dorofeev.weatherappwithcomposev2.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dorofeev.weatherappwithcomposev2.factories.ViewModelFactory
import com.dorofeev.weatherappwithcomposev2.viewmodels.DetailViewModel
import com.dorofeev.weatherappwithcomposev2.viewmodels.ShowWeatherViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModelDetail: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowWeatherViewModel::class)
    fun bindShowWeatherViewModel(viewModel: ShowWeatherViewModel): ViewModel


}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)