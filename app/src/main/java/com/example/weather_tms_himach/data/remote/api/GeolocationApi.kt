package com.example.weather_tms_himach.data.remote.api

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.dto.geolocation_dto.GeolocationRespDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface is used to obtain the "locationKey".
 * When asking, you need to consider the "TOP_LEVEL".
 * If the "TOP_LEVEL = true" allows you to get the exact name of the city.
 * Geolocation parameters must be formed from
 * the parameters of longitude and latitude. Geolocation
 * must be separated by a comma in this format "0.00,0.00".
 */
interface GeolocationApi {

    companion object {
        const val KEY_PARAM = "apikey"
        const val API_KEY = BuildConfig.API_KEY
        const val GEO_PARAM = "q"
        const val LANGUAGE_PARAM = "language"
        const val TOP_LEVEL_PARAM = "toplevel"
        const val TOP_LEVEL = "true"
    }

    @GET("locations/v1/cities/geoposition/search")
    suspend fun getCityApi(
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(GEO_PARAM) latAndLon: String,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(TOP_LEVEL_PARAM) topLevel: String = TOP_LEVEL
    ): GeolocationRespDto
}