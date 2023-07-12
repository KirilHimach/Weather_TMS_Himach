package com.example.weather_tms_himach.domain.models

data class FiveDaysForecast(
    val dayOfWeek: String? = "",
    val temperatureMin: Double? = 0.0,
    val temperatureMax: Double? = 0.0,
    val dayIcon: Int? = 0,
    val nightIcon: Int? = 0
)
