package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.data.remote.dto.five_days_for_nesteds.FiveDaysForDto
import com.google.gson.annotations.SerializedName

internal data class FiveDaysForListDto(
    @SerializedName("DailyForecasts")
    val fiveDaysForecastDtoDto: List<FiveDaysForDto>?
) {
    internal companion object {
        fun empty(): FiveDaysForListDto =
            FiveDaysForListDto(
                fiveDaysForecastDtoDto = emptyList()
            )
    }
}