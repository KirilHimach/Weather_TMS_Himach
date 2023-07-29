package com.example.weather_tms_himach.di.base

import com.example.weather_tms_himach.utils.authentication.Auth
import com.example.weather_tms_himach.utils.authentication.AuthImpl
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AuthModule {
    @Singleton
    @Provides
    internal fun provideFirebase() = Firebase

    @Singleton
    @Provides
    internal fun provideAuth(firebase: Firebase): Auth =
        AuthImpl(firebase = firebase)
}