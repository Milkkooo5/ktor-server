package com.example

import kotlinx.serialization.Serializable

@Serializable
data class AnalyzeInfo(
    val userId: String,
    val fileName: String,
    val result: String
)