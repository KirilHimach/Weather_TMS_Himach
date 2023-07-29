package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.data.remote.dto.five_days_for_nesteds.toFiveDaysForecast
import com.example.weather_tms_himach.data.remote.dto.toCurrentCondition
import com.example.weather_tms_himach.data.remote.dto.toGeolocation
import com.example.weather_tms_himach.data.remote.dto.toTwelveHoursForecast
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

internal class ForecastUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getCurCond(
        locationKey: String, language: String
    ): List<CurrentCondition> =
        weatherRepo.getCurCondRem(
            locationKey = locationKey, language = language
        ).map { currentCondition ->
            currentCondition.toCurrentCondition()
        }

    suspend fun getFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): List<FiveDaysForecast> =
        weatherRepo.getFiveDaysForRem(
            locationKey = locationKey, language = language, metric = metric
        ).fiveDaysForecastDtoDto!!.map { fiveDaysForDto ->
            fiveDaysForDto.toFiveDaysForecast()
        }

    suspend fun getGeo(
        latAndLon: String, language: String
    ): Geolocation =
        weatherRepo.getCityApiRem(
            latAndLon = latAndLon, language = language
        ).toGeolocation()

    suspend fun getTwelveHouFor(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForecast> =
        weatherRepo.getTweHouForRem(
            locationKey = locationKey, language = language, metric = metric
        ).map { forecast ->
            forecast.toTwelveHoursForecast()
        }
}