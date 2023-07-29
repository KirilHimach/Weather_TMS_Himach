package com.example.weather_tms_himach.data.remote.dto.five_days_for_nesteds

import com.google.gson.annotations.SerializedName

internal data class MinimumDto(
    @SerializedName("Value")
    val value: Double?
)