package com.example.weather_tms_himach.di.base

import com.example.weather_tms_himach.data.remote.api.CurrentConditionApi
import com.example.weather_tms_himach.data.remote.api.FiveDaysForecastApi
import com.example.weather_tms_himach.data.remote.api.GeolocationApi
import com.example.weather_tms_himach.data.remote.api.TwelveHoursForecastApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    private const val BASE_URL = "http://dataservice.accuweather.com/"

    @Singleton
    @Provides
    fun provideHttpClientLoginInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideCurCondApi(retrofit: Retrofit): CurrentConditionApi =
        retrofit.create(CurrentConditionApi::class.java)

    @Singleton
    @Provides
    fun provideFiveDaysForApi(retrofit: Retrofit): FiveDaysForecastApi =
        retrofit.create(FiveDaysForecastApi::class.java)

    @Singleton
    @Provides
    fun provideGeoApi(retrofit: Retrofit): GeolocationApi =
        retrofit.create(GeolocationApi::class.java)

    @Singleton
    @Provides
    fun provideTwelveHouForApi(retrofit: Retrofit): TwelveHoursForecastApi =
        retrofit.create(TwelveHoursForecastApi::class.java)
}