package com.example.weather_tms_himach.data.local.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statistics_param")
internal data class ParamStatisticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "location_key")
    val locationKey: Long?,
    @ColumnInfo(name = "weather_text")
    val weatherText: String?,
    @ColumnInfo(name = "weather_icon")
    val weatherIcon: Int?,
    @ColumnInfo(name = "temp")
    val temp: Double?,
    @ColumnInfo(name = "date")
    val date: String?
)