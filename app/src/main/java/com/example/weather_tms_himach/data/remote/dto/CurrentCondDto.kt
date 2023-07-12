package com.example.weather_tms_himach.data.remote.dto


import com.example.weather_tms_himach.data.local.StatisticsParam
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    val weatherText: String? = "",
    @SerializedName("WeatherIcon")
    val weatherIcon: Int? = 0,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature? = RealFeelTemperature(),
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int? = 0,
    @SerializedName("Wind")
    val wind: Wind? = Wind(),
    @SerializedName("UVIndex")
    val uVIndex: Int? = 0,
    @SerializedName("Visibility")
    val visibility: Visibility? = Visibility(),
    @SerializedName("Pressure")
    val pressure: Pressure? = Pressure(),
    @SerializedName("ApparentTemperature")
    val apparentTemperature: ApparentTemperature? = ApparentTemperature(),
    @SerializedName("Link")
    val link: String? = ""
) {
    data class RealFeelTemperature(
        @SerializedName("Metric")
        val metric: Metric? = Metric()
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double? = 0.0,
            @SerializedName("Unit")
            val unit: String? = ""
        )
    }
    data class Wind(
        @SerializedName("Speed")
        val speed: Speed? = Speed()
    ) {
        data class Speed(
            @SerializedName("Metric")
            val metric: Metric? = Metric()
        ) {
            data class Metric(
                @SerializedName("Value")
                val value: Double? = 0.0,
                @SerializedName("Unit")
                val unit: String? = ""
            )
        }
    }
    data class Visibility(
        @SerializedName("Metric")
        val metric: Metric? = Metric()
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double? = 0.0,
            @SerializedName("Unit")
            val unit: String? = ""
        )
    }
    data class Pressure(
        @SerializedName("Metric")
        val metric: Metric? = Metric()
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double? = 0.0,
            @SerializedName("Unit")
            val unit: String? = ""
        )
    }
    data class ApparentTemperature(
        @SerializedName("Metric")
        val metric: Metric? = Metric()
    ) {
        data class Metric(
            @SerializedName("Value")
            val value: Double? = 0.0,
            @SerializedName("Unit")
            val unit: String? = ""
        )
    }
}

internal fun CurrentCondDto.toCurrentCondition() =
    CurrentCondition(
        weatherText = weatherText,
        weatherIcon = weatherIcon,
        realFellTempMetVal = realFeelTemperature?.metric?.value,
        realFellTempMetUn = realFeelTemperature?.metric?.unit,
        relativeHumidity = relativeHumidity,
        windSpeedMetVal = wind?.speed?.metric?.value,
        windSpeedMetUn = wind?.speed?.metric?.unit,
        uVIndex = uVIndex,
        visibilityMetVal = visibility?.metric?.value,
        visibilityMetUn = visibility?.metric?.unit,
        pressureMetVal = pressure?.metric?.value,
        pressureMetUn = pressure?.metric?.unit,
        apparentTempMetVal = apparentTemperature?.metric?.value,
        apparentTempMetUn = apparentTemperature?.metric?.unit
    )

internal fun CurrentCondDto.toLocalCurrentCond(): StatisticsParam {
    val locationKey = link
        ?.substringBefore("?")
        ?.substringAfterLast("/")
        ?.toLong()
    val date = LocalDateTime.now()
    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:hh")
    return StatisticsParam(
        locationKey = locationKey,
        weatherText = weatherText,
        weatherIcon = weatherIcon,
        temp = realFeelTemperature?.metric?.value,
        date = date.format(pattern)
    )
}