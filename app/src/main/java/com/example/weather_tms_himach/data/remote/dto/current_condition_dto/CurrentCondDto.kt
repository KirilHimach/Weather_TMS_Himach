package com.example.weather_tms_himach.data.remote.dto.current_condition_dto

import com.google.gson.annotations.SerializedName

/**
 * This model serves to deserialize the current state of the weather.
 * The "Wind" contains two formats, Metric(km/h) and Imperial(mi/h).
 * The "RealFeelTemperature" contains two formats, Metric(C) and Imperial(F).
 * The "Visibility" contains two formats, Metric(km) and Imperial(mi).
 * The "Pressure" contains two formats, Metric(md) and Imperial(inHg).
 * WeatherText - "Sunny".
 */
data class CurrentCondDto(
    @SerializedName("WeatherText")
    val weatherText: String,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("UVIndex")
    val uVIndex: Int,
    @SerializedName("Visibility")
    val visibility: Visibility,
    @SerializedName("Pressure")
    val pressure: Pressure,
    @SerializedName("ApparentTemperature")
    val apparentTemperature: ApparentTemperature,
) {
    data class RealFeelTemperature(
        @SerializedName("Metric")
        val metric: Metric,
        @SerializedName("Imperial")
        val imperial: Imperial
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )

        data class Imperial(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )
    }


    data class Wind(
        @SerializedName("Speed")
        val speed: Speed
    ) {
        data class Speed(
            @SerializedName("Metric")
            val metric: Metric,
            @SerializedName("Imperial")
            val imperial: Imperial
        ) {
            data class Metric(
                @SerializedName("Value")
                val value: Double,
                @SerializedName("Unit")
                val unit: String
            )

            data class Imperial(
                @SerializedName("Value")
                val value: Double,
                @SerializedName("Unit")
                val unit: String
            )
        }
    }


    data class Visibility(
        @SerializedName("Metric")
        val metric: Metric,
        @SerializedName("Imperial")
        val imperial: Imperial
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )

        data class Imperial(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )
    }


    data class Pressure(
        @SerializedName("Metric")
        val metric: Metric,
        @SerializedName("Imperial")
        val imperial: Imperial
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )

        data class Imperial(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )
    }


    data class ApparentTemperature(
        @SerializedName("Metric")
        val metric: Metric,
        @SerializedName("Imperial")
        val imperial: Imperial
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )

        data class Imperial(
            @SerializedName("Value")
            val value: Double,
            @SerializedName("Unit")
            val unit: String
        )
    }
}
