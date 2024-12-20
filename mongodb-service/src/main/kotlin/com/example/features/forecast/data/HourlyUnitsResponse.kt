package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnitsResponse(
    val time: String,
    val temperature_2m: String,
    val weather_code: String,
)
