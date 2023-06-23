package com.example.weather_tms_himach.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.weather_tms_himach.data.local.entities.FiveDaysFor

@Dao
interface FiveDaysForecastDao { //TODO well be improve

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fiveDaysFor: FiveDaysFor)
}