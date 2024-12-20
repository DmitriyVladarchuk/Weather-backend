package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class DailyUnitsResponse(
    val time: String,
    val weather_code: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val sunrise: String,
    val sunset: String,
)