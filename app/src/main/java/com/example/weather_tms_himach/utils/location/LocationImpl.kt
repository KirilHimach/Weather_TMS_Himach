package com.example.weather_tms_himach.utils.location

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

class LocationImpl(_context: Application) : Location {
    private val context: Application
    private val locationClient: FusedLocationProviderClient
    private lateinit var location: String

    init {
        context = _context
        locationClient =
            LocationServices
                .getFusedLocationProviderClient(context)
        runBlocking {
            launch {
                onLastLocation(context)
            }
        }
    }

    override suspend fun getLastLocation(): String =
        location

    private suspend fun onLastLocation(context: Application) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        coroutineScope {
            launch {
                locationClient.lastLocation
                    .addOnSuccessListener { lastLocation ->
                        location = onFormatLatLon(
                            lat = lastLocation.latitude,
                            lon = lastLocation.longitude
                        )
                    }
            }
        }
    }

    private fun onFormatLatLon(lat: Double, lon: Double): String {
        val pattern = DecimalFormat("#.##")
        return "${pattern.format(lat)},${pattern.format(lon)}"
    }
}