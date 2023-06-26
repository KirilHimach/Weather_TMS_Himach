package com.example.weather_tms_himach.data.remote.dto.geolocation_dto

import com.example.weather_tms_himach.data.local.entities.City
import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the object
 * of the city of interest. The "Key" is used to obtain
 * weather data in the following steps. The name of the
 * city will be used on the main screen.
 */
data class GeolocationDto(
    @SerializedName("Key")
    val locationKey: String,
    @SerializedName("LocalizedName")
    val city: String
)

fun GeolocationDto.toLocalCity() =
    City(
        id = locationKey.toLong(),
        city = city
    )
