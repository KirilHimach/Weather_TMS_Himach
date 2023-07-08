package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.data.remote.dto.toCurrentCondition
import com.example.weather_tms_himach.data.remote.dto.toFiveDaysForecast
import com.example.weather_tms_himach.data.remote.dto.toGeolocation
import com.example.weather_tms_himach.data.remote.dto.toTwelveHoursForecast
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getCurCond(
        locationKey: String, language: String
    ): List<CurrentCondition> =
        weatherRepo.getRemCurCond(
            locationKey = locationKey, language = language
        )?.map { currentCondition ->
            currentCondition.toCurrentCondition()
        } ?: listOf(CurrentCondition())

    suspend fun getFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): List<FiveDaysForecast> =
        weatherRepo.getRemFiveDaysFor(
            locationKey = locationKey, language = language, metric = metric
        )?.fiveDaysForecastDto?.map { fiveDaysForDto ->
            fiveDaysForDto.toFiveDaysForecast()
        } ?: listOf(FiveDaysForecast())

    suspend fun getGeo(
        latAndLon: String, language: String
    ): Geolocation =
        weatherRepo.getRemGeo(
            latAndLon = latAndLon, language = language
        )?.toGeolocation() ?: Geolocation()

    suspend fun getTwelveHouFor(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForecast> =
        weatherRepo.getRemTweHouFor(
            locationKey = locationKey, language = language, metric = metric
        )?.map { forecast ->
            forecast.toTwelveHoursForecast()
        } ?: listOf(TwelveHoursForecast())
}