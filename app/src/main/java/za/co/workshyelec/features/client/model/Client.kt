package za.co.workshyelec.features.client.model

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val address: String,
    val clientType: String,
    val companyName: String,
    val createdAt: String,
    val createdBy: String?,
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val lastUpdatedBy: String?,
    val phoneNumber: String,
    val updatedAt: String
)