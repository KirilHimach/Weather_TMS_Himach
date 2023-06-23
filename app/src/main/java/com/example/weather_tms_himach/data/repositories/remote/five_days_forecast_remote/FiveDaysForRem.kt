package com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote

import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.FiveDaysForRespDto

interface FiveDaysForRem {
    suspend fun getFiveDaysFor(
        key: String, language: String, metric: Boolean
    ): FiveDaysForRespDto
}