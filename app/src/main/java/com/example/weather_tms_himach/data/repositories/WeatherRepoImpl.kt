package com.example.weather_tms_himach.data.repositories

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForListDto
import com.example.weather_tms_himach.data.remote.dto.CityApiDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import com.example.weather_tms_himach.data.remote.dto.toCityStatisticsEntity
import com.example.weather_tms_himach.data.remote.dto.toParamStatisticsEntity
import com.example.weather_tms_himach.data.repositories.local.LocalStatistics
import com.example.weather_tms_himach.data.repositories.remote.current_condition_remote.CurrentCondRem
import com.example.weather_tms_himach.data.repositories.remote.five_days_forecast_remote.FiveDaysForRem
import com.example.weather_tms_himach.data.repositories.remote.geolocation_remote.GeolocationRem
import com.example.weather_tms_himach.data.repositories.remote.twelve_hours_forecast_remote.TwelveHoursForRem
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

internal class WeatherRepoImpl @Inject constructor(
    private val currentCondRem: CurrentCondRem,
    private val fiveDaysForRem: FiveDaysForRem,
    private val geolocationRem: GeolocationRem,
    private val twelveHoursForRem: TwelveHoursForRem,
    private val localStatistics: LocalStatistics
) : WeatherRepo {
    override suspend fun getCurCondRem(
        locationKey: String, language: String
    ): List<CurrentCondDto> {
        val currentCondition = currentCondRem.getCurrentCond(
            locationKey = locationKey,
            language = language
        )
        if (currentCondition != null && currentCondition.isSuccessful) {
            localStatistics.insertParamStatistics(
                currentCondition.body()?.map { currentConditionDto ->
                    currentConditionDto.toParamStatisticsEntity()
                })
        }
        return currentCondition?.body() ?: listOf(CurrentCondDto.empty())
    }

    override suspend fun getFiveDaysForRem(
        locationKey: String, language: String, metric: Boolean
    ): FiveDaysForListDto {
        val fiveDaysFor = fiveDaysForRem.getFiveDaysFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )
        return if (fiveDaysFor != null && fiveDaysFor.isSuccessful) {
            fiveDaysFor.body() ?: FiveDaysForListDto.empty()
        } else {
            FiveDaysForListDto.empty()
        }
    }

    override suspend fun getCityApiRem(
        latAndLon: String, language: String
    ): CityApiDto {
        val geolocation = geolocationRem.getGeo(
            latAndLon = latAndLon,
            language = language
        )
        if (geolocation != null && geolocation.isSuccessful) {
            localStatistics.insertCityStatistics(
                cityStatisticsEntity = geolocation.body()?.toCityStatisticsEntity()
            )
        }
        return geolocation?.body() ?: CityApiDto.empty()
    }

    override suspend fun getTweHouForRem(
        locationKey: String, language: String, metric: Boolean
    ): List<TwelveHoursForDto> {
        val twelveHoursFor =  twelveHoursForRem.getTwelveHoursFor(
            locationKey = locationKey,
            language = language,
            metric = metric
        )
        return if (twelveHoursFor != null && twelveHoursFor.isSuccessful) {
            twelveHoursFor.body() ?: listOf(TwelveHoursForDto.empty())
        } else {
            listOf(TwelveHoursForDto.empty())
        }
    }

    override suspend fun getAllStatisticsLocal(): List<ForecastStatistics> =
        localStatistics.getAllStatistics() ?: emptyList()
}