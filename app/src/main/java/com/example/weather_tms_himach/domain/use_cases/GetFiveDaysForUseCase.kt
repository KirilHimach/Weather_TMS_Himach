package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetFiveDaysForUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getFiveDaysFor(): FiveDaysForecast =
        FiveDaysForecast() //TODO
}