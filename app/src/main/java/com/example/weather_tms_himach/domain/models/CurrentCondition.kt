package com.example.weather_tms_himach.domain.models

internal data class CurrentCondition(
    val weatherText: String? = "",
    val weatherIcon: Int? = 0,
    val realFellTempMetVal: Double? = 0.0,
    val realFellTempMetUn: String? = "",
    val relativeHumidity: Int? = 0,
    val windSpeedMetVal: Double? = 0.0,
    val windSpeedMetUn: String? = "",
    val uVIndex: Int? = 0,
    val visibilityMetVal: Double? = 0.0,
    val visibilityMetUn: String? = "",
    val pressureMetVal: Double? = 0.0,
    val pressureMetUn: String? = "",
    val apparentTempMetVal: Double? = 0.0,
    val apparentTempMetUn: String? = ""
)
