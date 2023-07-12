package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.data.local.visitedCityStatistics
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

class VisitedSitesUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getVisitedSitesStatistics(): List<VisitedCityStatistics> =
        weatherRepo.getAllStatistics().map { q ->
            q.visitedCityStatistics()
        }

    suspend fun onDeleteAllStatistics() =
        weatherRepo.onDeleteAllStatistics()

    suspend fun onDeleteStatisticsParamById(id: Long) =
        weatherRepo.onDeleteStatisticsParamById(id = id)
}