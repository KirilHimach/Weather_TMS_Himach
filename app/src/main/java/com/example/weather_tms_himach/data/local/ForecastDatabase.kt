package com.example.weather_tms_himach.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather_tms_himach.data.local.dao.CityDao
import com.example.weather_tms_himach.data.local.dao.FiveDaysForecastDao
import com.example.weather_tms_himach.data.local.dao.ForecastDao
import com.example.weather_tms_himach.data.local.entities.City
import com.example.weather_tms_himach.data.local.entities.FiveDaysForecast

@Database(entities = [City::class, FiveDaysForecast::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun fiveDaysForecastDao(): FiveDaysForecastDao
    abstract fun forecastDao(): ForecastDao
}