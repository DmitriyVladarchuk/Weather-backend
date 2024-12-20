package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsResponse(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val relative_humidity_2m: String,
    val precipitation: String,
    val weather_code: String,
    val pressure_msl: String,
    val wind_speed_10m: String,
)