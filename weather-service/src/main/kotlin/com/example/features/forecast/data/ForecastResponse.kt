package com.example.features.forecast.data

import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val latitude: Float,
    val longitude: Float,
    val generationtime_ms: Float,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Float,
    val current_units: CurrentUnitsResponse,
    val current: CurrentResponse,
    val hourly_units: HourlyUnitsResponse,
    val hourly: HourlyResponse,
    val daily_units: DailyUnitsResponse,
    val daily: DailyResponse,
)
