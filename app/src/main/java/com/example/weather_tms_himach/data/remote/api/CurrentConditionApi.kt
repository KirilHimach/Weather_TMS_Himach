package com.example.weather_tms_himach.data.remote.api

import com.example.weather_tms_himach.BuildConfig
import com.example.weather_tms_himach.data.remote.dto.current_condition_dto.CurrentConditionResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface is used to get the current weather.
 * When asking, you need to consider the "DETAILS".
 * If the "DETAILS = true" then the response will contain detailed
 * information about the weather.
 * The "locationKey" is used to obtain data for the city of interest.
 * Data can be obtained in any language of the world. In the request,
 * you need to enter the language code in this format "en".
 */
interface CurrentConditionApi {

    companion object {
        const val KEY_PARAM = "apikey"
        const val API_KEY = BuildConfig.API_KEY
        const val LANGUAGE_PARAM = "language"
        const val DETAILS_PARAM = "details"
        const val DETAILS = "true"
    }

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getCurrentCondition(
        @Path("locationKey") locationKey: String,
        @Query(KEY_PARAM) apiKey: String = API_KEY,
        @Query(LANGUAGE_PARAM) language: String,
        @Query(DETAILS_PARAM) details: String = DETAILS
    ): CurrentConditionResponseDto
}