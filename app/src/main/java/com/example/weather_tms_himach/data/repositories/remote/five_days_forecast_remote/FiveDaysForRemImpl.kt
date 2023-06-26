package com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote

import com.example.weather_tms_himach.data.remote.api.FiveDaysForecastApi
import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.FiveDaysForRespDto
import javax.inject.Inject

class FiveDaysForRemImpl @Inject constructor(
    private val fiveDaysForecastApi: FiveDaysForecastApi
) : FiveDaysForRem {
    override suspend fun getFiveDaysFor(
        key: String, language: String, metric: Boolean
    ): FiveDaysForRespDto =
        fiveDaysForecastApi.getFiveDaysForecast(
            locationKey = key,
            language = language,
            metric = metric
        )
}