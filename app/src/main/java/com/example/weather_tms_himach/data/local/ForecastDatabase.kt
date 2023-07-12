package com.example.weather_tms_himach.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StatisticsCity::class, StatisticsParam::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun visitedSitesDao(): StatisticsDao
}