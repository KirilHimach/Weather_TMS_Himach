package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
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

fun TwelveHoursForDto.toTwelveHoursForecast(): TwelveHoursForecast {
    val dateFormat = dateTime
        .substringAfter("T")
        .substringBefore(":")
    return TwelveHoursForecast(
        date = dateFormat,
        weatherIcon = weatherIcon,
        temperature = temperature.value
    )
}
