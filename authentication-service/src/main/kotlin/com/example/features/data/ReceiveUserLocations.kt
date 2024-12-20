package com.example.features.data

import kotlinx.serialization.Serializable

@Serializable
data class ReceiveUserLocations(
    val userId: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val country: String?,
    val admin1: String?,
    val isSelected: Boolean = false
)
