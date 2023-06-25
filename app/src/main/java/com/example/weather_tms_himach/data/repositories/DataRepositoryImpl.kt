package com.example.weather_tms_himach.data.repositories

import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto.TwelveHoursForDto
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
    override suspend fun getRemCurCond(): List<CurrentCondDto> =
        currentCondRem.getCurrentCond(
            key = "28580", //TODO
            language = "en"
        ).currentCondDto

    override suspend fun getRemFiveDaysFor(): List<FiveDaysForDto> =
        fiveDaysForRem.getFiveDaysFor(
            key = "28580", //TODO
            language = "en",
            metric = true
        ).fiveDaysForecastDto

    override suspend fun getRemGeo(): GeolocationDto =
        geolocationRem.getGeo(
            latAndLon = "53.9,27.56", //TODO
            language = "en"
        ).geolocationDto

    override suspend fun getRemTweHouFor(): List<TwelveHoursForDto> =
        twelveHoursForRem.getTwelveHoursFor(
            key = "28580", //TODO
            language = "en",
            metric = true
        ).twelveHoursForDto

    override suspend fun getLocFor() {
        TODO("Not yet implemented")
    }
}