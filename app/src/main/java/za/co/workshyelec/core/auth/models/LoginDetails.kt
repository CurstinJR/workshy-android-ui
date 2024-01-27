package za.co.workshyelec.core.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginDetails(
    val username: String,
    val password: String
)