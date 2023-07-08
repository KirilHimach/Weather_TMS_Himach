package com.example.weather_tms_himach.domain.models

data class TwelveHoursForecast(
    val date: String? = "2011-12-03T10:15:30+01:00",
    val weatherIcon: Int? = 0,
    val temperature: Double? = 0.0
)
