package com.example.weather_tms_himach.data.remote.api

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.dto.CurrentCondDto
import com.example.weather_tms_himach.data.remote.dto.FiveDaysForListDto
import com.example.weather_tms_himach.data.remote.dto.CityApiDto
import com.example.weather_tms_himach.data.remote.dto.TwelveHoursForDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ForecastApi {
    private companion object {
        const val KEY_PARAM = "apikey"
        const val API_KEY = BuildConfig.API_KEY
        const val LOCATION_PARAM = "locationKey"
        const val LANGUAGE_PARAM = "language"
        const val DETAILS_PARAM = "details"
        const val GEO_PARAM = "q"
        const val TOP_LEVEL_PARAM = "toplevel"
        const val TOP_LEVEL_VAL = true
        const val METRIC_PARAM = "metric"
        const val METRIC_VAL = true
    }

    @GET("locations/v1/cities/geoposition/search")
    suspend fun getCityApi(
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(GEO_PARAM) latAndLon: String,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(TOP_LEVEL_PARAM) topLevel: Boolean = TOP_LEVEL_VAL
    ): Response<CityApiDto>?

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getListCurrentCondition(
        @Path(LOCATION_PARAM) locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(DETAILS_PARAM) details: Boolean = METRIC_VAL
    ): Response<List<CurrentCondDto>>?

    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getFiveDaysForecast(
        @Path(LOCATION_PARAM) locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(METRIC_PARAM) metric: Boolean
    ): Response<FiveDaysForListDto>?

    @GET("forecasts/v1/hourly/12hour/{locationKey}")
    suspend fun getListTwelveHoursForecast(
        @Path(LOCATION_PARAM) locationKey:String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(METRIC_PARAM) metric: Boolean
    ): Response<List<TwelveHoursForDto>>?
}