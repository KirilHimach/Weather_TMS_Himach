package com.example.weather_tms_himach.di.base

import android.app.Application
import androidx.room.Room
import com.example.weather_tms_himach.data.local.ForecastDatabase
import com.example.weather_tms_himach.data.local.ForecastStatistics
import com.example.weather_tms_himach.data.local.StatisticsCity
import com.example.weather_tms_himach.data.local.StatisticsParam
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DBModule {
    @Singleton
    @Provides
    internal fun provideDatabase(context: Application): ForecastDatabase =
        Room.databaseBuilder(
            context = context,
            klass = ForecastDatabase::class.java,
            name = "weather_tms"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    internal fun visitedSitesDao(db: ForecastDatabase) =
        db.visitedSitesDao()

    @Provides
    internal fun provideCityEntity() = StatisticsCity()

    @Provides
    internal fun provideLocalCurrentCondEntity() = StatisticsParam()
}