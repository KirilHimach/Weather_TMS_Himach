package com.example.weather_tms_himach.data.repositories

import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import com.example.weather_tms_himach.data.repositories.remote.current_condition_remote.CurrentCondRem
import com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote.FiveDaysForRem
import com.example.weather_tms_himach.data.repositories.remote.geolocation_remote.GeolocationRem
import com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote.TwelveHoursForRem
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val currentCondRem: CurrentCondRem,
    private val fiveDaysForRem: FiveDaysForRem,
    private val geolocationRem: GeolocationRem,
    private val twelveHoursForRem: TwelveHoursForRem
) : WeatherRepo {
    override suspend fun getRemCurCond(
        locationKey: String, language: String
    ): List<CurrentCondDto>? =
        currentCondRem.getCurrentCond(
            locationKey = locationKey,
            language = language
        )?.body()

    override suspend fun getRemFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): FiveDaysForDto? =
        fiveDaysForRem.getFiveDaysFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )?.body()

    override suspend fun getRemGeo(
        latAndLon: String, language: String
    ): GeolocationDto? =
        geolocationRem.getGeo(
            latAndLon = latAndLon,
            language = language
        )?.body()

    override suspend fun getRemTweHouFor(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForDto>? =
        twelveHoursForRem.getTwelveHoursFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )?.body()

    override suspend fun getLocFor() {
        TODO("Not yet implemented")
    }
}