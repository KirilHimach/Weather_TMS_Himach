package com.example.weather_tms_himach.data.repositories.remote.geolocation_remote

import com.example.weather_tms_himach.data.remote.api.ForecastApi
import com.example.weather_tms_himach.data.remote.dto.CityApiDto
import retrofit2.Response
import javax.inject.Inject

internal class GeolocationRemImpl @Inject constructor(
    private val forecastApi: ForecastApi
) : GeolocationRem {
    override suspend fun getGeo(
        latAndLon: String,
        language: String
    ): Response<CityApiDto>? =
        forecastApi.getCityApi(
            latAndLon = latAndLon,
            language = language
        )
}