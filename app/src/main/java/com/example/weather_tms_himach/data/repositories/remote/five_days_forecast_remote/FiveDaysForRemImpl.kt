package com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote

import com.example.weather_tms_himach.data.remote.api.ForecastApi
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForDto
import retrofit2.Response
import javax.inject.Inject

class FiveDaysForRemImpl @Inject constructor(
    private val forecastApi: ForecastApi
) : FiveDaysForRem {
    override suspend fun getFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): Response<FiveDaysForDto>? =
        forecastApi.getFiveDaysForecast(
            locationKey = locationKey,
            language = language,
            metric = metric
        )
}