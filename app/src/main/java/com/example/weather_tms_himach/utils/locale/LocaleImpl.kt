package com.example.weather_tms_himach.utils.locale

import android.content.res.Configuration
import javax.inject.Inject

internal class LocaleImpl @Inject constructor(
    private val resources: Configuration
) : Locale {
    override suspend fun getMainLanguageTag(): String =
        getFormatTag()

    private fun getFormatTag(): String =
        getLocale().substringBefore("-")

    private fun getLocale(): String =
        resources.locales.get(0).toLanguageTag()
}