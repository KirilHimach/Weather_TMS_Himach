package com.example.weather_tms_himach.data.repositories.remote.current_condition_remote

import com.example.weather_tms_himach.data.remote.api.CurrentConditionApi
import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.CurrentCondRespDto
import javax.inject.Inject

class CurrentCondRemImpl @Inject constructor(
    private val currentConditionApi: CurrentConditionApi
) : CurrentCondRem {
    override suspend fun getCurrentCond(
        key: String, language: String
    ): CurrentCondRespDto =
        currentConditionApi.getCurrentCondition(
            locationKey = key,
            language = language
        )
}