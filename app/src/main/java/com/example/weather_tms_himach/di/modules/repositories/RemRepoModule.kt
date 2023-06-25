package com.example.weather_tms_himach.di.modules.repositories

import com.example.weather_tms_himach.data.remote.api.CurrentConditionApi
import com.example.weather_tms_himach.data.remote.api.FiveDaysForecastApi
import com.example.weather_tms_himach.data.remote.api.GeolocationApi
import com.example.weather_tms_himach.data.remote.api.TwelveHoursForecastApi
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
        currentConditionApi: CurrentConditionApi
    ): CurrentCondRem =
        CurrentCondRemImpl(currentConditionApi)

    @Provides
    fun provideFiveDaysForRem(
        fiveDaysForecastApi: FiveDaysForecastApi
    ): FiveDaysForRem =
        FiveDaysForRemImpl(fiveDaysForecastApi)

    @Provides
    fun provideGeoRem(
        geolocationApi: GeolocationApi
    ): GeolocationRem =
        GeolocationRemImpl(geolocationApi)

    @Provides
    fun provideTwelveHouForRem(
        twelveHoursForecastApi: TwelveHoursForecastApi
    ): TwelveHoursForRem =
        TwelveHoursForRemImpl(twelveHoursForecastApi)
}