package com.example.weather_tms_himach.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey()         //TODO apiCity
    var id: Long? = 0,
    @ColumnInfo("city")
    var city: String? = null
)
