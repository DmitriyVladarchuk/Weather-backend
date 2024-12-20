package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class DailyResponse(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Float>,
    val temperature_2m_min: List<Float>,
    val sunrise: List<String>,
    val sunset: List<String>,
)
