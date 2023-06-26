package com.example.weather_tms_himach.di.base

import android.app.Application
import androidx.room.Room
import com.example.weather_tms_himach.data.local.ForecastDatabase
import com.example.weather_tms_himach.data.local.entities.City
import com.example.weather_tms_himach.data.local.entities.FiveDaysFor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DBModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Application): ForecastDatabase =
        Room.databaseBuilder(
            context = context,
            klass = ForecastDatabase::class.java,
            name = "weather_tms"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCityDao(db: ForecastDatabase) =
        db.cityDao()

    @Singleton
    @Provides
    fun provideFiveDaysForDao(db: ForecastDatabase) =
        db.fiveDaysForecastDao()

    @Singleton
    @Provides
    fun provideForecastDao(db:ForecastDatabase) =
        db.forecastDao()

    @Provides
    fun provideCityEnt() = City()

    @Provides
    fun provideFiveDaysForEnt() = FiveDaysFor()
}