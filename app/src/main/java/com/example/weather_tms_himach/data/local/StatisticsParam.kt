package com.example.weather_tms_himach.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "statistics_param")
data class StatisticsParam(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "location_key")
    val locationKey: Long? = 0,
    @ColumnInfo(name = "weather_text")
    val weatherText: String? = null,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon: Int? = 0,
    @ColumnInfo(name = "temp")
    val temp: Double? = 0.0,
    @ColumnInfo(name = "date")
    val date: String? = null
)