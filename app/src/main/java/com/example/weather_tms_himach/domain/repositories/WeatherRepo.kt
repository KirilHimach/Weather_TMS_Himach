package com.example.weather_tms_himach.domain.repositories


interface WeatherRepo {
    suspend fun getRemCurCond()//: CurrentConditionDto
    suspend fun getRemFiveDaysFor()//: List<FiveDaysForecastDto>
    suspend fun getRemGeo()//: GeolocationDto
    suspend fun getRemTweHouFor()//: List<TwelveHoursForecastDto>
    suspend fun getLocFor()
}