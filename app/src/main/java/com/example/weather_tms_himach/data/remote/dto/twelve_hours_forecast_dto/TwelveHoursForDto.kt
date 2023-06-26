package com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto

import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the 12-hour forecast of the weather.
 */
data class TwelveHoursForDto(
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("Temperature")
    val temperature: Temperature
) {
    data class Temperature(
        @SerializedName("Value")
        val value: Double
    )
}