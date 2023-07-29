package com.example.weather_tms_himach.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_tms_himach.data.local.entites.CityStatisticsEntity
import com.example.weather_tms_himach.data.local.entites.ParamStatisticsEntity

@Dao
internal interface ForecastStatisticsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatisticsCity(cityStatisticsEntity: CityStatisticsEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatisticsParam(statisticParams: List<ParamStatisticsEntity>?)

    @Query(
        "SELECT * FROM statistics_sites, statistics_param WHERE " +
                "statistics_param.location_key == statistics_sites.id"
    )
    suspend fun getAllStatistics(): List<ForecastStatistics>?
}