package com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto

import com.example.weather_tms_himach.data.local.entities.FiveDaysFor
import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the 5-day forecast of the weather.
 * The "Date" contains this format "2023-06-17T07:00:00+03:00".
 * In this case, the link is needed to retrieve the "locationKey".
 * This key is needed to link to tables.
 */
data class FiveDaysForDto(
    @SerializedName("Date")
    val date: String,
    @SerializedName("Temperature")
    val temperature: Temperature,
    @SerializedName("Day")
    val day: Day,
    @SerializedName("Night")
    val night: Night,
    @SerializedName("Link")
    val link: String
) {
    data class Temperature(
        @SerializedName("Minimum")
        val minimum: Minimum,
        @SerializedName("Maximum")
        val maximum: Maximum
    ) {
        data class Minimum(
            @SerializedName("Value")
            val value: Double
        )

        data class Maximum(
            @SerializedName("Value")
            val value: Double
        )
    }

    data class Day(
        @SerializedName("Icon")
        val icon: Int
    )

    data class Night(
        @SerializedName("Icon")
        val icon: Int
    )
}

fun FiveDaysForDto.toLocalForecast(): FiveDaysFor {
    val locationKey = link
        .substringBefore("?")
        .substringAfterLast("/")
        .toLong()
    return FiveDaysFor(
        date = date,
        tMin = temperature.minimum.value.toString(),
        tMax = temperature.maximum.value.toString(),
        dayIcon = day.icon,
        nightIcon = night.icon,
        cityId = locationKey
    )
}