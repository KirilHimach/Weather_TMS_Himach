package com.example.weather_tms_himach.data.local.models

import androidx.room.ColumnInfo

data class Forecast(
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("date")
    var date: String? = null,
    @ColumnInfo("temperature_min")
    var tMin: String? = null,
    @ColumnInfo("temperature_max")
    var tMax: String? = null,
    @ColumnInfo("day_icon")
    var dayIcon: Int? = 0,
    @ColumnInfo("night_icon")
    var nightIcon: Int? = 0
)
