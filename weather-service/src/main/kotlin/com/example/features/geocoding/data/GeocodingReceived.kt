package com.example.features.geocoding.data

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingReceived(
    val results: List<LocationReceived> = listOf(),
    val generationtime_ms: Float,
)
