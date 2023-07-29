package com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote

import com.example.weather_tms_himach.data.remote.api.ForecastApi
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import retrofit2.Response
import javax.inject.Inject

internal class TwelveHoursForRemImpl @Inject constructor(
    private val forecastApi: ForecastApi
) : TwelveHoursForRem {
    override suspend fun getTwelveHoursFor(
        locationKey: String,
        language: String,
        metric: Boolean
    ): Response<List<TwelveHoursForDto>>? =
        forecastApi.getListTwelveHoursForecast(
            locationKey = locationKey,
            language = language,
            metric = metric
        )
}