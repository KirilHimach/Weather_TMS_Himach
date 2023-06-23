package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetTwelveHouForUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getTwelveHouFor(): TwelveHoursForecast =
        TwelveHoursForecast() //TODO
}