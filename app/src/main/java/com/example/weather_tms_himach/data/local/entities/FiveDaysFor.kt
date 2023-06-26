package com.example.weather_tms_himach.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FiveDaysFor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo("date")
    var date: String? = null,
    @ColumnInfo("temperature_min")
    var tMin: String? = null,
    @ColumnInfo("temperature_max")
    var tMax: String? = null,
    @ColumnInfo("day_icon")
    var dayIcon: Int? = 0,
    @ColumnInfo("night_icon")
    var nightIcon: Int? = 0,
    @ColumnInfo(name = "city_id")
    var cityId: Long? = 0
)
