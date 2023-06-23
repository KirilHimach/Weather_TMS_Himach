package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetGeoUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getGeo(): Geolocation =
        Geolocation() //TODO
}