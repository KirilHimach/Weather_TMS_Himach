package com.example.weather_tms_himach.data.repositories.local

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.local.entites.CityStatisticsEntity
import com.example.weather_tms_himach.data.local.entites.ParamStatisticsEntity

internal interface LocalStatistics {
    suspend fun insertCityStatistics(cityStatisticsEntity: CityStatisticsEntity?)
    suspend fun insertParamStatistics(statisticParams: List<ParamStatisticsEntity>?)
    suspend fun getAllStatistics(): List<ForecastStatistics>?
}