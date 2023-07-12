package com.example.weather_tms_himach.data.repositories.local


import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.local.StatisticsCity
import com.example.weather_tms_himach.data.local.StatisticsParam

interface LocalStatistics {
    suspend fun insertStatisticsCity(statisticsCity: StatisticsCity?)
    suspend fun insertStatisticsParam(statisticParams: List<StatisticsParam>?)
    suspend fun getAll(): List<ForecastStatistics>?
    suspend fun deleteAllStatistics()
    suspend fun deleteParamById(id: Long?)
}