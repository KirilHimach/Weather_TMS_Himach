package com.example.weather_tms_himach.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather_tms_himach.presentation.view_models.ForecastViewModel
import com.example.weather_tms_himach.presentation.view_models.SignUpViewModel
import com.example.weather_tms_himach.presentation.view_models.SignInViewModel
import com.example.weather_tms_himach.presentation.view_models.VisitedSitesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun createAccountViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    fun forecastViewModel(viewModel: ForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VisitedSitesViewModel::class)
    fun visitedCitesViewModel(viewModel: VisitedSitesViewModel): ViewModel
}