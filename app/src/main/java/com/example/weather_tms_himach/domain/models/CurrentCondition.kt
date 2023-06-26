package com.example.weather_tms_himach.domain.models

data class CurrentCondition(
    val weatherText: String? = "",
    val weatherIcon: Int? = 0,
    val realFellTempMetVal: Double? = 0.0,
    val realFellTempMetUn: String? = "",
    val realFellTempImpVal: Double? = 0.0,
    val realFellTempImpUn: String? = "",
    val relativeHumidity: Int? = 0,
    val windSpeedMetVal: Double? = 0.0,
    val windSpeedMetUn: String? = "",
    val windSpeedImpVal: Double? = 0.0,
    val windSpeedImpUn: String? = "",
    val uVIndex: Int? = 0,
    val visibilityMetVal: Double? = 0.0,
    val visibilityMetUn: String? = "",
    val visibilityImpVal: Double? = 0.0,
    val visibilityImplUn: String? = "",
    val pressureMetVal: Double? = 0.0,
    val pressureMetUn: String? = "",
    val pressureImplVal: Double? = 0.0,
    val pressureImplUn: String = "",
    val apparentTempMetVal: Double? = 0.0,
    val apparentTempMetUn: String? = "",
    val apparentTempImpVal: Double? = 0.0,
    val apparentTempImpUn: String? = ""
)
