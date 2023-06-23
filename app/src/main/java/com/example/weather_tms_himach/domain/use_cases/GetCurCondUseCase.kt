package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class GetCurCondUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getCurCond(): CurrentCondition =
        CurrentCondition() //TODO
}