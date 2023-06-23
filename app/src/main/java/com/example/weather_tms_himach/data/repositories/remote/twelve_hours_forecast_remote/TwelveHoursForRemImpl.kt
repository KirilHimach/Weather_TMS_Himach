package com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote

import com.example.weather_tms_himach.data.remote.api.TwelveHoursForecastApi
import com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto.TwelveHoursForRespDto
import javax.inject.Inject

class TwelveHoursForRemImpl @Inject constructor(
    private val twelveHoursForecastApi: TwelveHoursForecastApi
) : TwelveHoursForRem {
    override suspend fun getTwelveHoursFor(
        key: String, language: String, metric: Boolean
    ): TwelveHoursForRespDto =
        twelveHoursForecastApi.getTwelveHoursForecast(
            locationKey = key,
            language = language,
            metric = metric
        )
}