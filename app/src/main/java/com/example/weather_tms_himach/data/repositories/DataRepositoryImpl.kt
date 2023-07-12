package com.example.weather_tms_himach.data.repositories

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import com.example.weather_tms_himach.data.remote.dto.toLocalCity
import com.example.weather_tms_himach.data.remote.dto.toLocalCurrentCond
import com.example.weather_tms_himach.data.repositories.local.LocalStatistics
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
    private val twelveHoursForRem: TwelveHoursForRem,
    private val localStatistics: LocalStatistics
) : WeatherRepo {
    override suspend fun getRemCurCond(
        locationKey: String, language: String
    ): List<CurrentCondDto> {
        val currentCondition = currentCondRem.getCurrentCond(
            locationKey = locationKey,
            language = language
        )?.body()
        if (currentCondition != null) {
            localStatistics.insertStatisticsParam(currentCondition.map {
                it.toLocalCurrentCond()
            })
        }
        return currentCondition ?: listOf(CurrentCondDto())
    }

    override suspend fun getRemFiveDaysFor(
        locationKey: String, language: String, metric: Boolean
    ): FiveDaysForDto =
        fiveDaysForRem.getFiveDaysFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )?.body() ?: FiveDaysForDto()

    override suspend fun getRemGeo(
        latAndLon: String, language: String
    ): GeolocationDto {
        val geolocation = geolocationRem.getGeo(
                latAndLon = latAndLon,
                language = language
            )?.body()
        if (geolocation != null) {
            localStatistics.insertStatisticsCity(geolocation.toLocalCity())
        }
        return geolocation ?: GeolocationDto()
    }

    override suspend fun getRemTweHouFor(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForDto> =
        twelveHoursForRem.getTwelveHoursFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )?.body() ?: listOf(TwelveHoursForDto())

    override suspend fun getAllStatistics(): List<ForecastStatistics> =
        localStatistics.getAll() ?: emptyList()

    override suspend fun onDeleteAllStatistics() =
        localStatistics.deleteAllStatistics()

    override suspend fun onDeleteStatisticsParamById(id: Long) =
        localStatistics.deleteParamById(id = id)
}