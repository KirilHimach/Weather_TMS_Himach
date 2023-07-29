package com.example.weather_tms_himach.di.base

import android.app.Application
import androidx.room.Room
import com.example.weather_tms_himach.data.local.ForecastStatisticsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DBModule {
    @Singleton
    @Provides
    internal fun provideDatabase(context: Application): ForecastStatisticsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = ForecastStatisticsDatabase::class.java,
            name = "weather_tms"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    internal fun visitedSitesDao(db: ForecastStatisticsDatabase) =
        db.visitedSitesDao()
}