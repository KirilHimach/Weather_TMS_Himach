package com.example.weather_tms_himach.utils.location

import android.location.Location
import com.google.android.gms.location.LocationListener
import kotlinx.coroutines.flow.Flow


interface CurrentLocation {
    suspend fun getLastLocation(): Location?
    suspend fun startLocationUpdates(listener: LocationListener)
    suspend fun removeUpdates(locationListener: LocationListener)
}