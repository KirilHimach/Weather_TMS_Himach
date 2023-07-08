package com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote

import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import retrofit2.Response

interface TwelveHoursForRem {
    suspend fun getTwelveHoursFor(
        locationKey: String, language: String, metric: Boolean
    ): Response<List<TwelveHoursForDto>>?
}