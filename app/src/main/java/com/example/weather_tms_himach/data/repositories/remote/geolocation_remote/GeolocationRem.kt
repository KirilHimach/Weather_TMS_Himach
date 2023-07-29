package com.example.weather_tms_himach.data.repositories.remote.geolocation_remote

import com.example.weather_tms_himach.data.remote.dto.CityApiDto
import retrofit2.Response

internal interface GeolocationRem {
    suspend fun getGeo(
        latAndLon: String,
        language: String
    ): Response<CityApiDto>?
}