package com.example.weather_tms_himach.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather_tms_himach.data.local.entites.CityStatisticsEntity
import com.example.weather_tms_himach.data.local.entites.ParamStatisticsEntity

@Database(
    entities = [
        CityStatisticsEntity::class,
        ParamStatisticsEntity::class
    ],
    version = 4
)
internal abstract class ForecastStatisticsDatabase : RoomDatabase() {
    abstract fun visitedSitesDao(): ForecastStatisticsDao
}