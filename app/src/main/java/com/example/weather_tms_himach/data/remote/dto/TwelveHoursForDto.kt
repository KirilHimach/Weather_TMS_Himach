package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.data.remote.dto.twelve_hours_for_nesteds.TwelveHoursForTempDto
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.google.gson.annotations.SerializedName

internal data class TwelveHoursForDto(
    @SerializedName("DateTime")
    val dateTime: String?,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int?,
    @SerializedName("Temperature")
    val temperature: TwelveHoursForTempDto?
) {
    internal companion object {
        fun empty(): TwelveHoursForDto =
            TwelveHoursForDto(
                dateTime = "",
                weatherIcon = 0,
                temperature = TwelveHoursForTempDto.empty()
            )
    }
}

internal fun TwelveHoursForDto.toTwelveHoursForecast(): TwelveHoursForecast {
    val dateFormat = dateTime
        ?.substringAfter("T")
        ?.substringBefore(":")
    return TwelveHoursForecast(
        date = dateFormat,
        weatherIcon = weatherIcon,
        temperature = temperature?.value
    )
}
