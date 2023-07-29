package com.example.weather_tms_himach.domain.models

internal data class VisitedCityStatistics(
  val city: String? = "",
  val weatherText: String? = "",
  val icon: Int? = 0,
  val temp: Double? = 0.0,
  val date: String? = ""
)