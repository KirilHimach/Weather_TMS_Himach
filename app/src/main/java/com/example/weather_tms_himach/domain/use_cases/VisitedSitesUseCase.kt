package com.example.weather_tms_himach.domain.use_cases

import com.example.weather_tms_himach.data.local.toVisitedCityStatistics
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import javax.inject.Inject

internal class VisitedSitesUseCase @Inject constructor(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getVisitedSitesStatistics(): List<VisitedCityStatistics> =
        weatherRepo.getAllStatisticsLocal().map { statistics ->
            statistics.toVisitedCityStatistics()
        }
}