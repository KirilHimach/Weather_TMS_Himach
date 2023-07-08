package com.example.weather_tms_himach.di.modules.repositories

import com.example.weather_tms_himach.data.remote.api.ForecastApi
import com.example.weather_tms_himach.data.repositories.remote.current_condition_remote.CurrentCondRem
import com.example.weather_tms_himach.data.repositories.remote.current_condition_remote.CurrentCondRemImpl
import com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote.FiveDaysForRem
import com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote.FiveDaysForRemImpl
import com.example.weather_tms_himach.data.repositories.remote.geolocation_remote.GeolocationRem
import com.example.weather_tms_himach.data.repositories.remote.geolocation_remote.GeolocationRemImpl
import com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote.TwelveHoursForRem
import com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote.TwelveHoursForRemImpl
import dagger.Module
import dagger.Provides

@Module
class RemRepoModule {

    @Provides
    fun provideCurCondRem(
        forecastApi: ForecastApi
    ): CurrentCondRem =
        CurrentCondRemImpl(forecastApi)

    @Provides
    fun provideFiveDaysForRem(
        forecastApi: ForecastApi
    ): FiveDaysForRem =
        FiveDaysForRemImpl(forecastApi)

    @Provides
    fun provideGeoRem(
        forecastApi: ForecastApi
    ): GeolocationRem =
        GeolocationRemImpl(forecastApi)

    @Provides
    fun provideTwelveHouForRem(
        forecastApi: ForecastApi
    ): TwelveHoursForRem =
        TwelveHoursForRemImpl(forecastApi)
}