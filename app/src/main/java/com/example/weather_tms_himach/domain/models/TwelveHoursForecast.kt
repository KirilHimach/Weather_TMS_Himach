package com.example.weather_tms_himach.domain.models

internal data class TwelveHoursForecast(
    val date: String? = "",
    val weatherIcon: Int? = 0,
    val temperature: Double? = 0.0
)
