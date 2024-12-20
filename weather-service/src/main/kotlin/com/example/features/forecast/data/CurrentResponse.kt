package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    val time: String,
    val interval: Int,
    val temperature_2m: Float,
    val relative_humidity_2m: Int,
    val precipitation: Float,
    val weather_code: Int,
    val pressure_msl: Float,
    val wind_speed_10m: Float,
)
