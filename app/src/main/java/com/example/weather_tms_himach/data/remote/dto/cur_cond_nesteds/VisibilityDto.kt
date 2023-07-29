package com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds

import com.google.gson.annotations.SerializedName

internal data class VisibilityDto(
    @SerializedName("Metric")
    val metricDto: MetricDto?
) {
    internal companion object {
        fun empty(): VisibilityDto =
            VisibilityDto(
                metricDto = MetricDto.empty()
            )
    }
}
