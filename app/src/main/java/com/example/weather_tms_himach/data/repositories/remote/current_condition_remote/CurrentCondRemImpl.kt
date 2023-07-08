package com.example.weather_tms_himach.data.repositories.remote.current_condition_remote

import com.example.weather_tms_himach.data.remote.api.ForecastApi
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import retrofit2.Response
import javax.inject.Inject

class CurrentCondRemImpl @Inject constructor(
    private val forecastApi: ForecastApi
) : CurrentCondRem {
    override suspend fun getCurrentCond(
        locationKey: String, language: String
    ): Response<List<CurrentCondDto>>? =
        forecastApi.getCurrentCondition(
            locationKey = locationKey,
            language = language
        )
}