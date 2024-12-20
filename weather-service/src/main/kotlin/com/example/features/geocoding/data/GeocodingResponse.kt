package com.example.features.geocoding.data

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val results: List<LocationResponse>,
)