package com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds

import com.google.gson.annotations.SerializedName

internal data class WindDto(
    @SerializedName("Speed")
    val speedDto: SpeedDto?
) {
    internal companion object {
        fun empty(): WindDto =
            WindDto(
                speedDto = SpeedDto.empty()
            )
    }
}
