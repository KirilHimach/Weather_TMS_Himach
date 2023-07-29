package com.example.weather_tms_himach.di.base

import android.content.res.Configuration
import android.content.res.Resources
import com.example.weather_tms_himach.utils.locale.Locale
import com.example.weather_tms_himach.utils.locale.LocaleImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object ResourcesModule {
    @Singleton
    @Provides
    internal fun provideResources(): Configuration =
        Resources.getSystem().configuration

    @Singleton
    @Provides
    internal fun provideLocale(configuration: Configuration): Locale =
        LocaleImpl(configuration)
}