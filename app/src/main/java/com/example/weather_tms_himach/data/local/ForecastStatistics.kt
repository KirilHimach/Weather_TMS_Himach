package com.example.weather_tms_himach.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import kotlin.reflect.KClass

data class ForecastStatistics(
    @ColumnInfo(name = "city")
    val city: String? = "",
    @ColumnInfo(name = "weather_text")
    val weatherText: String? = "",
    @ColumnInfo(name = "weather_icon")
    val icon: Int? = 0,
    @ColumnInfo(name = "temp")
    val temp: Double? = 0.0,
    @ColumnInfo(name = "date")
    val date: String? = ""
)

internal fun ForecastStatistics.visitedCityStatistics() =
    VisitedCityStatistics(
        city = city,
        weatherText = weatherText,
        icon = icon,
        temp = temp,
        date = date
    )