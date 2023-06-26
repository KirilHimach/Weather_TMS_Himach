package com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote

import com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto.TwelveHoursForRespDto

interface TwelveHoursForRem {
    suspend fun getTwelveHoursFor(
        key: String, language: String, metric: Boolean
    ): TwelveHoursForRespDto
}