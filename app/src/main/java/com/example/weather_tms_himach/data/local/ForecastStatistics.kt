package com.example.weather_tms_himach.data.local

import androidx.room.ColumnInfo
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics

internal data class ForecastStatistics(
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "weather_text")
    val weatherText: String?,
    @ColumnInfo(name = "weather_icon")
    val icon: Int?,
    @ColumnInfo(name = "temp")
    val temp: Double?,
    @ColumnInfo(name = "date")
    val date: String?
)

internal fun ForecastStatistics.toVisitedCityStatistics() = 
    VisitedCityStatistics(
        city = city,
        weatherText = weatherText,
        icon = icon,
        temp = temp,
        date = date
    )