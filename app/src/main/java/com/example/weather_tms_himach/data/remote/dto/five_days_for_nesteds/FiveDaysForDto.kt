package com.example.weather_tms_himach.data.remote.dto.five_days_for_nesteds

import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.google.gson.annotations.SerializedName

internal data class FiveDaysForDto(
    @SerializedName("Date")
    val date: String?,
    @SerializedName("Temperature")
    val temperatureDto: TemperatureDto?,
    @SerializedName("Day")
    val dayDto: DayDto?,
    @SerializedName("Night")
    val nightDto: NightDto?
)

internal fun FiveDaysForDto.toFiveDaysForecast() =
    FiveDaysForecast(
        dayOfWeek = date,
        temperatureMin = temperatureDto?.minimumDto?.value,
        temperatureMax = temperatureDto?.maximumDto?.value,
        dayIcon = dayDto?.icon,
        nightIcon = nightDto?.icon
    )
