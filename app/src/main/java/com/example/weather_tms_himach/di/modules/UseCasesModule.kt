package com.example.weather_tms_himach.di.modules

import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import com.example.weather_tms_himach.domain.use_cases.GetCurCondUseCase
import com.example.weather_tms_himach.domain.use_cases.GetFiveDaysForUseCase
import com.example.weather_tms_himach.domain.use_cases.GetGeoUseCase
import com.example.weather_tms_himach.domain.use_cases.GetTwelveHouForUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideCurCondUseCase(weatherRepo: WeatherRepo) =
        GetCurCondUseCase(weatherRepo)

    @Provides
    fun provideFiveDaysForUseCase(weatherRepo: WeatherRepo) =
        GetFiveDaysForUseCase(weatherRepo)

    @Provides
    fun provideGeoUseCase(weatherRepo: WeatherRepo) =
        GetGeoUseCase(weatherRepo)

    @Provides
    fun provideTwelveHouForUseCase(weatherRepo: WeatherRepo) =
        GetTwelveHouForUseCase(weatherRepo)
}