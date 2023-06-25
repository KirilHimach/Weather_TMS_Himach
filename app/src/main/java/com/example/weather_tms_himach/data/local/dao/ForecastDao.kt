package com.example.weather_tms_himach.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import com.example.weather_tms_himach.data.local.models.Forecast

//TODO well be improve. This query will combine
// data from City and FiveDaysForecastDao tables.
@Dao
interface ForecastDao {

   // suspend fun getForecast(): Forecast
}