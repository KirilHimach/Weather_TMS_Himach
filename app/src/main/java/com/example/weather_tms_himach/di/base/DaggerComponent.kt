package com.example.weather_tms_himach.di.base

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DBModule::class
    ]
)
interface DaggerComponent {

    interface Factory {
        fun create(@BindsInstance application: Application): DaggerComponent
    }
}