package com.example.weather_tms_himach.di.base

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.api.ForecastApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideHttpClientLoginInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideConverter(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, converter: GsonConverterFactory
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()

    @Singleton
    @Provides
    fun provideCurCondApi(retrofit: Retrofit): ForecastApi =
        retrofit.create(ForecastApi::class.java)
}