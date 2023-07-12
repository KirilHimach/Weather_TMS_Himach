package com.example.weather_tms_himach.domain.repositories

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto


interface WeatherRepo {
    suspend fun getRemCurCond(
        locationKey: String, language: String
    ): List<CurrentCondDto>

    suspend fun getRemFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): FiveDaysForDto

    suspend fun getRemGeo(
        latAndLon: String, language: String
    ): GeolocationDto

    suspend fun getRemTweHouFor(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForDto>

    suspend fun getAllStatistics(): List<ForecastStatistics>

    suspend fun onDeleteAllStatistics()
    suspend fun onDeleteStatisticsParamById(id: Long)
}