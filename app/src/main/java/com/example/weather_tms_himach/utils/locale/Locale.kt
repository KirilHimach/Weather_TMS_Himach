package com.example.weather_tms_himach.utils.locale

internal interface Locale {
    suspend fun getMainLanguageTag(): String
}