package com.example.weather_tms_himach.di.modules.repositories

import com.example.weather_tms_himach.data.local.ForecastStatisticsDao
import com.example.weather_tms_himach.data.repositories.local.LocalStatistics
import com.example.weather_tms_himach.data.repositories.local.LocalStatisticsImpl
import dagger.Module
import dagger.Provides

@Module
internal class LocalRepoModule {
    @Provides
    internal fun provideCityLocal(
        forecastStatisticsDao: ForecastStatisticsDao
    ): LocalStatistics =
        LocalStatisticsImpl(forecastStatisticsDao)
}