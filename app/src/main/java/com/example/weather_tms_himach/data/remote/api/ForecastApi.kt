package com.example.weather_tms_himach.data.remote.api

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForDto
import com.example.weather_tms_himach.data.remote.dto.GeolocationDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApi {

    companion object {
        const val KEY_PARAM = "apikey"
        const val API_KEY = BuildConfig.API_KEY
        const val LANGUAGE_PARAM = "language"
        const val DETAILS_PARAM = "details"
        const val GEO_PARAM = "q"
        const val TOP_LEVEL_PARAM = "toplevel"
        const val METRIC_PARAM = "metric"
        const val DETAILS = true
    }

    /**
     * This interface is used to get the current weather.
     * When asking, you need to consider the "DETAILS".
     * If the "DETAILS = true" then the response will contain detailed
     * information about the weather.
     * The "locationKey" is used to obtain data for the city of interest.
     * Data can be obtained in any language of the world. In the request,
     * you need to enter the language code in this format "en".
     */

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getCurrentCondition(
        @Path("locationKey") locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(DETAILS_PARAM) details: Boolean = DETAILS
    ): Response<List<CurrentCondDto>>?

    /**
     * This interface is used to request a 5-day weather forecast.
     * Parameter "METRIC_PARAM" must be taken into account when querying.
     * Parameter "METRIC_PARAM = true" returns data in format "C",
     * "METRIC_PARAM = false" returns data in format "F".
     * Data can be obtained in any language of the world. In the request,
     * you need to enter the language code in this format "en".
     */
    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getFiveDaysForecast(
        @Path("locationKey") locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(METRIC_PARAM) metric: Boolean
    ): Response<FiveDaysForDto>?

    /**
     * This interface is used to obtain the "locationKey".
     * When asking, you need to consider the "TOP_LEVEL".
     * If the "TOP_LEVEL = true" allows you to get the exact name of the city.
     * Geolocation parameters must be formed from
     * the parameters of longitude and latitude. Geolocation
     * must be separated by a comma in this format "0.00,0.00".
     */
    @GET("locations/v1/cities/geoposition/search")
    suspend fun getCityApi(
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(GEO_PARAM) latAndLon: String,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(TOP_LEVEL_PARAM) topLevel: Boolean = DETAILS
    ): Response<GeolocationDto>?

    /**
     * This interface is used to request a 12-hour weather forecast.
     * Parameter "METRIC_PARAM" must be taken into account when querying.
     * Parameter "METRIC_PARAM = true" returns data in format "C",
     * "METRIC_PARAM = false" returns data in format "F".
     * Data can be obtained in any language of the world. In the request,
     * you need to enter the language code in this format "en".
     */
    @GET("forecasts/v1/hourly/12hour/{locationKey}")
    suspend fun getTwelveHoursForecast(
        @Path("locationKey") locationKey:String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(METRIC_PARAM) metric: Boolean
    ): Response<List<TwelveHoursForDto>>?
}