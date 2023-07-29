package com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote

import com.example.weather_tms_himach.data.remote.dto.FiveDaysForListDto
import retrofit2.Response

internal interface FiveDaysForRem {
    suspend fun getFiveDaysFor(
        locationKey: String,
        language: String,
        metric: Boolean
    ): Response<FiveDaysForListDto>?
}