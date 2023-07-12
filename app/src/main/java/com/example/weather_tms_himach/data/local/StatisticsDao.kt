package com.example.weather_tms_himach.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StatisticsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatisticsCity(statisticsCity: StatisticsCity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatisticsParam(statisticParams: List<StatisticsParam>?)

    @Query(
        "SELECT * FROM statistics_sites, statistics_param WHERE " +
                "statistics_param.location_key == statistics_sites.id"
    )
    suspend fun getAll(): List<ForecastStatistics>?

    @Query("DELETE FROM statistics_sites")
    suspend fun deleteAllVisitedCites()

    @Query("DELETE FROM statistics_param")
    suspend fun deleteAllStatisticsParam()

    @Query("DELETE FROM statistics_param WHERE location_key = :id")
    suspend fun deleteParamById(id: Long?)
}