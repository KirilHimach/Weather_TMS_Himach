package com.example.weather_tms_himach.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather_tms_himach.presentation.view_models.SignUpViewModel
import com.example.weather_tms_himach.presentation.view_models.SignInViewModel
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
}