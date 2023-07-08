package com.example.weather_tms_himach.domain.models

data class FiveDaysForecast(
    val dayOfWeek: String? = "2011-12-03T10:15:30+01:00",
    val temperatureMin: Double? = 0.0,
    val temperatureMax: Double? = 0.0,
    val dayIcon: Int? = 0,
    val nightIcon: Int? = 0
)
