package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class HourlyResponse(
    val time: List<String>,
    val temperature_2m: List<Float>,
    val weather_code: List<Int>,
)
