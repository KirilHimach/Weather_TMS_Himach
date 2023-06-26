package com.example.weather_tms_himach.di.base

import com.example.weather_tms_himach.domain.authentication.AuthImpl
import com.example.weather_tms_himach.domain.authentication.BaseAuth
import com.example.weather_tms_himach.domain.authentication.BaseAuthImpl
import com.example.weather_tms_himach.domain.authentication.Auth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object Auth {

    @Singleton
    @Provides
    fun provideAuth(): BaseAuth =
        BaseAuthImpl()

    @Singleton
    @Provides
    fun provideBaseAuth(baseAuth: BaseAuth): Auth =
        AuthImpl(baseAuth)
}