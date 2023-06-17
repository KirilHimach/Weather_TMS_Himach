package com.example.weather_tms_himach.data.remote.api

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.dto.five_days_forecast_dto.FiveDaysForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface is used to request a 5-day weather forecast.
 * Parameter "METRIC_PARAM" must be taken into account when querying.
 * Parameter "METRIC_PARAM = true" returns data in format "C",
 * "METRIC_PARAM = false" returns data in format "F".
 * Data can be obtained in any language of the world. In the request,
 * you need to enter the language code in this format "en".
 */
interface FiveDaysForecastApi {

    companion object {
        const val KEY_PARAM = "apikey"
        const val API_KEY = BuildConfig.API_KEY
        const val LANGUAGE_PARAM = "language"
        const val METRIC_PARAM = "metric"
    }

    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getFiveDaysForecast(
        @Path("locationKey") locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(METRIC_PARAM) metric: Boolean
    ): FiveDaysForecastResponseDto
}