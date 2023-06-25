package com.example.weather_tms_himach.domain.repositories

import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.twelve_hours_forecast_dto.TwelveHoursForDto


interface WeatherRepo {
    suspend fun getRemCurCond(): List<CurrentCondDto>
    suspend fun getRemFiveDaysFor(): List<FiveDaysForDto>
    suspend fun getRemGeo(): GeolocationDto
    suspend fun getRemTweHouFor(): List<TwelveHoursForDto>
    suspend fun getLocFor()
}