package com.example.weather_tms_himach.domain.repositories

import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast


interface WeatherRepo {
    suspend fun getRemCurCond(): List<CurrentCondition>
    suspend fun getRemFiveDaysFor(): List<FiveDaysForecast>
    suspend fun getRemGeo(): Geolocation
    suspend fun getRemTweHouFor(): List<TwelveHoursForecast>
    suspend fun getLocFor()
}