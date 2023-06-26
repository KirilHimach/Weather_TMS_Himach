package com.example.weather_tms_himach.data.repositories.remote.current_condition_remote

import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.CurrentCondRespDto

interface CurrentCondRem {
    suspend fun getCurrentCond(
        key: String, language: String
    ): CurrentCondRespDto
}