package com.example.weather_tms_himach.di.base

import android.app.Application
import com.example.weather_tms_himach.utils.location.CurrentLocation
import com.example.weather_tms_himach.utils.location.CurrentLocationImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal object LocationModule {
    @Singleton
    @Provides
    internal fun provideFusedLocationProviderClient(
        context: Application
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Singleton
    @Provides
    internal fun provideLocationRequest(): LocationRequest =
        LocationRequest
            .Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                TimeUnit.SECONDS.toMillis(3)
            )
            .build()

    @Singleton
    @Provides
    internal fun provideCurrentLocation(
        context: Application,
        locationClient: FusedLocationProviderClient,
        locationRequest: LocationRequest
    ): CurrentLocation =
        CurrentLocationImpl(
            context = context,
            locationClient = locationClient,
            locationRequest = locationRequest
        )
}