package com.example.features.geocoding.data

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val country: String? = null,
    val admin1: String? = null,
)
