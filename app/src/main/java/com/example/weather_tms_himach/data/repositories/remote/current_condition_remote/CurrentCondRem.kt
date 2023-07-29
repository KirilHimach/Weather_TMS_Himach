package com.example.weather_tms_himach.data.repositories.remote.current_condition_remote

import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import retrofit2.Response

internal interface CurrentCondRem {
    suspend fun getCurrentCond(
        locationKey: String,
        language: String
    ): Response<List<CurrentCondDto>>?
}