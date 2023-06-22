package com.example.weather_tms_himach.utils.location

import android.content.Context

interface Location {
    suspend fun getLastLocation(): String
}