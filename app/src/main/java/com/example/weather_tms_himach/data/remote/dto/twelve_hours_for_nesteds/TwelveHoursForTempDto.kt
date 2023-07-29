package com.example.weather_tms_himach.data.remote.dto.twelve_hours_for_nesteds

import com.google.gson.annotations.SerializedName

internal data class TwelveHoursForTempDto(
    @SerializedName("Value")
    val value: Double?
) {
    internal companion object {
        fun empty(): TwelveHoursForTempDto =
            TwelveHoursForTempDto(
                value = 0.0
            )
    }
}