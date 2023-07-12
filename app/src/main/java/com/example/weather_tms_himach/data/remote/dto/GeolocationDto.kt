package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.data.local.StatisticsCity
import com.example.weather_tms_himach.domain.models.Geolocation
import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the object
 * of the city of interest. The "Key" is used to obtain
 * weather data in the following steps. The name of the
 * city will be used on the main screen.
 */
data class GeolocationDto(
    @SerializedName("Key")
    val locationKey: String? = "0",
    @SerializedName("LocalizedName")
    val city: String? = ""
)

internal fun GeolocationDto.toLocalCity() =
    StatisticsCity(
        id = locationKey?.toLong(),
        city = city
    )

internal fun GeolocationDto.toGeolocation() =
    Geolocation(
        locationKey = locationKey,
        city = city
    )

