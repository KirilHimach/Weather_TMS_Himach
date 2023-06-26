package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.toCurrentCondition
import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.toFiveDaysForecast
import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.toGeolocation
import com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto.toTwelveHoursForecast
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
        weatherRepo.getRemCurCond()!!.map { currentCondDto ->
            currentCondDto.toCurrentCondition()
        }

    suspend fun getFiveDaysFor(): List<FiveDaysForecast> =
        weatherRepo.getRemFiveDaysFor()!!.map { fiveDaysForDto ->
            fiveDaysForDto.toFiveDaysForecast()
        }

    suspend fun getGeo(): Geolocation =
        weatherRepo.getRemGeo()!!.toGeolocation()

    suspend fun getTwelveHouFor(): List<TwelveHoursForecast> =
        weatherRepo.getRemTweHouFor()!!.map { forecast ->
            forecast.toTwelveHoursForecast()
        }
}