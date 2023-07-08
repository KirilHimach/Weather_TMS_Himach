package com.example.weather_tms_himach.utils.locale

interface Locale {
    suspend fun getMainLanguageTag(): String
}