package com.example.weather_tms_himach.data.repositories.local

import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.local.StatisticsCity
import com.example.weather_tms_himach.data.local.StatisticsParam
import com.example.weather_tms_himach.data.local.StatisticsDao
import javax.inject.Inject

class LocalStatisticsImpl @Inject constructor(
    private val statisticsDao: StatisticsDao
) : LocalStatistics {
    override suspend fun insertStatisticsCity(statisticsCity: StatisticsCity?) =
        statisticsDao.insertStatisticsCity(statisticsCity = statisticsCity)

    override suspend fun insertStatisticsParam(statisticParams: List<StatisticsParam>?) =
        statisticsDao.insertStatisticsParam(statisticParams = statisticParams)

    override suspend fun getAll(): List<ForecastStatistics>? =
        statisticsDao.getAll()

    override suspend fun deleteAllStatistics() {
        statisticsDao.deleteAllVisitedCites()
        statisticsDao.deleteAllStatisticsParam()
    }

    override suspend fun deleteParamById(id: Long?) =
        statisticsDao.deleteParamById(id = id)
}