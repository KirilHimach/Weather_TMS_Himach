package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getCurCond(): List<CurrentCondition> =
        weatherRepo.getRemCurCond()

    suspend fun getFiveDaysFor(): List<FiveDaysForecast> =
        weatherRepo.getRemFiveDaysFor()

    suspend fun getGeo(): Geolocation =
        weatherRepo.getRemGeo()

    suspend fun getTwelveHouFor(): List<TwelveHoursForecast> =
        weatherRepo.getRemTweHouFor()
}