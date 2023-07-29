package com.example.weather_tms_himach.domain.repositories

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForListDto
import com.example.weather_tms_himach.data.remote.dto.CityApiDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto

internal interface WeatherRepo {
    suspend fun getCurCondRem(
        locationKey: String, language: String
    ): List<CurrentCondDto>

    suspend fun getFiveDaysForRem(
        locationKey: String, language: String, metric: Boolean
    ): FiveDaysForListDto

    suspend fun getCityApiRem(
        latAndLon: String, language: String
    ): CityApiDto

    suspend fun getTweHouForRem(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForDto>

    suspend fun getAllStatisticsLocal(): List<ForecastStatistics>
}