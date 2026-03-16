package com.example

import kotlinx.serialization.Serializable

@Serializable
data class UsersInfo(
    val firebaseId: String,
    val name: String,
    val surname: String
)

