package com.example.weather_tms_himach.data.repositories.remote.geolocation_remote

import com.example.weather_tms_himach.data.remote.dto.GeolocationDto
import retrofit2.Response

interface GeolocationRem {
    suspend fun getGeo(
        latAndLon: String, language: String
    ): Response<GeolocationDto>?
}