package com.example.features.data

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthorization(
    val email: String,
    val password: String,
)
