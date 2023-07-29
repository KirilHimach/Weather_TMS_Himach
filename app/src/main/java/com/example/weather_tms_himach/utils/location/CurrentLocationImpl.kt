package com.example.weather_tms_himach.utils.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

internal class CurrentLocationImpl @Inject constructor(
    private val context: Application,
    private val locationClient: FusedLocationProviderClient,
    private val locationRequest: LocationRequest
) : CurrentLocation {
    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getLastLocation(): Location? {
        if (!isGranted()) {
            return null
        }
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                addOnSuccessListener {
                    cont.resume(it) {}
                }
                addOnFailureListener {
                    cont.resume(null) {}
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun startLocationUpdates(listener: LocationListener) {
        if (!isGranted()) {
            return
        }
        locationClient.requestLocationUpdates(
            locationRequest,
            listener,
            Looper.getMainLooper()
        )
    }

    override suspend fun removeUpdates(locationListener: LocationListener) {
        locationClient.removeLocationUpdates(locationListener)
    }

    private fun isGranted(): Boolean {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val locationManager = context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return !(!isGpsEnabled &&
                !(hasAccessFineLocationPermission || hasAccessCoarseLocationPermission)
                )
    }
}