package com.example.weather_tms_himach.data.repositories.remote.geolocation_remote

import com.example.weather_tms_himach.data.remote.api.GeolocationApi
import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.GeolocationRespDto
import javax.inject.Inject

class GeolocationRemImpl @Inject constructor(
    private val geolocationApi: GeolocationApi
) : GeolocationRem {
    override suspend fun getGeo(
        latAndLon: String, language: String
    ): GeolocationRespDto =
        geolocationApi.getCityApi(
            latAndLon = latAndLon,
            language = language
        )
}