package com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds

import com.google.gson.annotations.SerializedName

internal data class MetricDto(
    @SerializedName("Value")
    val value: Double?,
    @SerializedName("Unit")
    val unit: String?
) {
    internal companion object {
        fun empty(): MetricDto =
            MetricDto(
                value = 0.0,
                unit = ""
            )
    }
}
