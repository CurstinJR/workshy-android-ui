package za.co.workshyelec.core.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val statusCode: Int,
    val message: String,
    val timestamp: String,
    val path: String
)