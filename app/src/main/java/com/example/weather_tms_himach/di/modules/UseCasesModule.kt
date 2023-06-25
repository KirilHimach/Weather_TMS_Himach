package com.example.weather_tms_himach.di.modules

import com.example.weather_tms_himach.domain.repositories.WeatherRepo
import com.example.weather_tms_himach.domain.use_cases.ForecastUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideForecastUseCase(weatherRepo: WeatherRepo) =
        ForecastUseCase(weatherRepo)
}