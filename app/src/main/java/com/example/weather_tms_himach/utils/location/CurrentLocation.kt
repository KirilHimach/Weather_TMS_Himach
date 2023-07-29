package com.example.weather_tms_himach.utils.location

import android.location.Location
import com.google.android.gms.location.LocationListener

internal interface CurrentLocation {
    suspend fun getLastLocation(): Location?
    suspend fun startLocationUpdates(listener: LocationListener)
    suspend fun removeUpdates(locationListener: LocationListener)
}