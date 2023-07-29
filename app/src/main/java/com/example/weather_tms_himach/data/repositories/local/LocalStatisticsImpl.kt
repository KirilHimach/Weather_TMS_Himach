package com.example.weather_tms_himach.data.repositories.local

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.local.entites.CityStatisticsEntity
import com.example.weather_tms_himach.data.local.entites.ParamStatisticsEntity
import com.example.weather_tms_himach.data.local.ForecastStatisticsDao
import javax.inject.Inject

internal class LocalStatisticsImpl @Inject constructor(
    private val forecastStatisticsDao: ForecastStatisticsDao
) : LocalStatistics {
    override suspend fun insertCityStatistics(cityStatisticsEntity: CityStatisticsEntity?) =
        forecastStatisticsDao.insertStatisticsCity(cityStatisticsEntity = cityStatisticsEntity)

    override suspend fun insertParamStatistics(statisticParams: List<ParamStatisticsEntity>?) =
        forecastStatisticsDao.insertStatisticsParam(statisticParams = statisticParams)

    override suspend fun getAllStatistics(): List<ForecastStatistics>? = //TODO
        forecastStatisticsDao.getAllStatistics()
}