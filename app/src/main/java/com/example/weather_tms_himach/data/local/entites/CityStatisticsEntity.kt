package com.example.weather_tms_himach.data.local.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statistics_sites")
internal data class CityStatisticsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long? = 0,
    @ColumnInfo(name = "city")
    val city: String?
)
