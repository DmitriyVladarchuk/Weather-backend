package com.example.features.data

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingInfo(
    val name: String,
    val language: String,
)
