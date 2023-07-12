package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the 5-day forecast of the weather.
 * The "Date" contains this format "2023-06-17T07:00:00+03:00".
 */
data class FiveDaysForDto(
    @SerializedName("DailyForecasts")
    val fiveDaysForecastDto: List<FiveDaysFor>? = listOf(FiveDaysFor())
) {
    data class FiveDaysFor(
        @SerializedName("Date")
        val date: String? = "",
        @SerializedName("Temperature")
        val temperature: Temperature? = Temperature(),
        @SerializedName("Day")
        val day: Day? = Day(),
        @SerializedName("Night")
        val night: Night? = Night()
    ) {
        data class Temperature(
            @SerializedName("Minimum")
            val minimum: Minimum? = Minimum(),
            @SerializedName("Maximum")
            val maximum: Maximum? = Maximum()
        ) {
            data class Minimum(
                @SerializedName("Value")
                val value: Double? = 0.0
            )
            data class Maximum(
                @SerializedName("Value")
                val value: Double? = 0.0
            )
        }
        data class Day(
            @SerializedName("Icon")
            val icon: Int? = 0
        )
        data class Night(
            @SerializedName("Icon")
            val icon: Int? = 0
        )
    }
}

internal fun FiveDaysForDto.FiveDaysFor.toFiveDaysForecast() =
    FiveDaysForecast(
        dayOfWeek = date,
        temperatureMin = temperature?.minimum?.value,
        temperatureMax = temperature?.maximum?.value,
        dayIcon = day?.icon,
        nightIcon = night?.icon
    )