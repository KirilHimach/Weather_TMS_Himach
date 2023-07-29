package com.example.weather_tms_himach.data.remote.dto


import com.example.weather_tms_himach.data.local.entites.ParamStatisticsEntity
import com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds.ApparentTemperatureDto
import com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds.PressureDto
import com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds.RealFeelTemperatureDto
import com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds.VisibilityDto
import com.example.weather_tms_himach.data.remote.dto.cur_cond_nesteds.WindDto
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal data class CurrentCondDto(
    @SerializedName("WeatherText")
    val weatherText: String?,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int?,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperatureDto: RealFeelTemperatureDto?,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int?,
    @SerializedName("Wind")
    val windDto: WindDto?,
    @SerializedName("UVIndex")
    val uVIndex: Int?,
    @SerializedName("Visibility")
    val visibilityDto: VisibilityDto?,
    @SerializedName("Pressure")
    val pressureDto: PressureDto?,
    @SerializedName("ApparentTemperature")
    val apparentTemperatureDto: ApparentTemperatureDto?,
    @SerializedName("Link")
    val link: String?
) {
    internal companion object {
        fun empty(): CurrentCondDto =
            CurrentCondDto(
                weatherText = "",
                weatherIcon = 0,
                realFeelTemperatureDto = RealFeelTemperatureDto.empty(),
                relativeHumidity = 0,
                windDto = WindDto.empty(),
                uVIndex = 0,
                visibilityDto = VisibilityDto.empty(),
                pressureDto = PressureDto.empty(),
                apparentTemperatureDto = ApparentTemperatureDto.empty(),
                link = ""
            )
    }
}

internal fun CurrentCondDto.toCurrentCondition() =
    CurrentCondition(
        weatherText = weatherText,
        weatherIcon = weatherIcon,
        realFellTempMetVal = realFeelTemperatureDto?.metricDto?.value,
        realFellTempMetUn = realFeelTemperatureDto?.metricDto?.unit,
        relativeHumidity = relativeHumidity,
        windSpeedMetVal = windDto?.speedDto?.metricDto?.value,
        windSpeedMetUn = windDto?.speedDto?.metricDto?.unit,
        uVIndex = uVIndex,
        visibilityMetVal = visibilityDto?.metricDto?.value,
        visibilityMetUn = visibilityDto?.metricDto?.unit,
        pressureMetVal = pressureDto?.metricDto?.value,
        pressureMetUn = pressureDto?.metricDto?.unit,
        apparentTempMetVal = apparentTemperatureDto?.metricDto?.value,
        apparentTempMetUn = apparentTemperatureDto?.metricDto?.unit
    )

internal fun CurrentCondDto.toParamStatisticsEntity(): ParamStatisticsEntity {
    val locationKey = link
        ?.substringBefore("?")
        ?.substringAfterLast("/")
        ?.toLong()
    val date = LocalDateTime.now()
    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:hh")
    return ParamStatisticsEntity(
        locationKey = locationKey!!,
        weatherText = weatherText!!,
        weatherIcon = weatherIcon!!,
        temp = realFeelTemperatureDto?.metricDto?.value!!,
        date = date.format(pattern)
    )
}