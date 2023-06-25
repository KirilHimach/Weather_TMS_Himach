package com.example.weather_tms_himach.di.base

import android.app.Application
import com.example.weather_tms_himach.di.modules.UseCasesModule
import com.example.weather_tms_himach.di.modules.ViewModelModule
import com.example.weather_tms_himach.di.modules.repositories.DataRepoModule
import com.example.weather_tms_himach.di.modules.repositories.RemRepoModule
import com.example.weather_tms_himach.presentation.fragments.login_fragments.CreateAccountFragment
import com.example.weather_tms_himach.presentation.fragments.login_fragments.SignInFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DBModule::class,
        DataRepoModule::class,
        RemRepoModule::class,
        UseCasesModule::class,
        ViewModelModule::class
    ]
)
interface DaggerComponent {
    fun inject(fragment: CreateAccountFragment)
    fun inject(fragment: SignInFragment)

    interface Factory {
        fun create(@BindsInstance application: Application): DaggerComponent
    }
}