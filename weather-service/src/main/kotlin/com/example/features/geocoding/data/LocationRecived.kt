package com.example.features.geocoding.data

import kotlinx.serialization.Serializable

@Serializable
data class LocationReceived(
    val id: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Float? = null,
    val feature_code: String? = null,
    val country_code: String? = null,
    val admin1_id: Int? = null,
    val admin2_id: Int? = null,
    val admin3_id: Int? = null,
    val admin4_id: Int? = null,
    val timezone: String? = null,
    val population: Int? = null,
    val postcodes: List<String>? = null,
    val country_id: Int? = null,
    val country: String? = null,
    val admin1: String? = null,
    val admin2: String? = null,
    val admin3: String? = null,
    val admin4: String? = null,
)