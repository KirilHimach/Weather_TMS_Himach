package com.example.weather_tms_himach.utils.locale

import android.content.res.Resources

/**
 * This class provides the system's current
 * primary language in the format "en-US".
 */
class LocaleImpl: Locale {
    private val resource = Resources.getSystem().configuration

    override fun getMainLanguageTag(): String =
        resource.locales.get(0).toLanguageTag()
}