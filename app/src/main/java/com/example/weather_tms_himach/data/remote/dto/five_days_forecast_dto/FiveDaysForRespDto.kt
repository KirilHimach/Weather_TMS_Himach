package com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto

import com.google.gson.annotations.SerializedName

data class FiveDaysForRespDto(
    @SerializedName("DailyForecasts")
    val fiveDaysForecastDto: List<FiveDaysForDto>?
)