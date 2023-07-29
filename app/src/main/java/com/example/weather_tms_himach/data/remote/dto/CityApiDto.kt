package com.example.weather_tms_himach.data.remote.dto

import com.example.weather_tms_himach.data.local.entites.CityStatisticsEntity
import com.example.weather_tms_himach.domain.models.Geolocation
import com.google.gson.annotations.SerializedName

internal data class CityApiDto(
    @SerializedName("Key")
    val locationKey: String?,
    @SerializedName("LocalizedName")
    val city: String?
) {
    internal companion object {
        fun empty(): CityApiDto =
            CityApiDto(
                locationKey = "",
                city = ""
            )
    }
}

internal fun CityApiDto.toCityStatisticsEntity() =
    CityStatisticsEntity(
        id = locationKey?.toLong(),
        city = city ?: ""
    )

internal fun CityApiDto.toGeolocation() =
    Geolocation(
        locationKey = locationKey,
        city = city
    )

