package com.example.weather_tms_himach.di.modules.repositories

import com.example.weather_tms_himach.data.repositories.DataRepositoryImpl
import com.example.weather_tms_himach.data.repositories.remote.current_condition_remote.CurrentCondRem
import com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote.FiveDaysForRem
import com.example.weather_tms_himach.data.repositories.remote.geolocation_remote.GeolocationRem
import com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote.TwelveHoursForRem
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import dagger.Module
import dagger.Provides

@Module
class DataRepoModule {

    @Provides
    fun provideWeatherRepo(
        currentCondRem: CurrentCondRem,
        fiveDaysForRem: FiveDaysForRem,
        geolocationRem: GeolocationRem,
        twelveHoursForRem: TwelveHoursForRem
    ): WeatherRepo =
        DataRepositoryImpl(
            currentCondRem = currentCondRem,
            fiveDaysForRem = fiveDaysForRem,
            geolocationRem = geolocationRem,
            twelveHoursForRem = twelveHoursForRem
        )
}