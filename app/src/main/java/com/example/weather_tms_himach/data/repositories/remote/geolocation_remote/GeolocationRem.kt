package com.example.weather_tms_himach.data.repositories.remote.geolocation_remote

import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.GeolocationRespDto

interface GeolocationRem {
    suspend fun getGeo(
        latAndLon: String, language: String
    ): GeolocationRespDto
}