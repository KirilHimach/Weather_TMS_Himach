package com.example.weather_tms_himach.di.base

import com.example.weather_tms_himach.domain.authentication.Auth
import com.example.weather_tms_himach.domain.authentication.AuthImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AuthModule {
    @Singleton
    @Provides
    internal fun provideAuth(): Auth =
        AuthImpl()
}