package za.co.workshyelec.features.client.model

import kotlinx.serialization.Serializable
import za.co.workshyelec.core.common.BaseModel

@Serializable
data class Client(
    val id: String,
    val address: String,
    val clientType: String,
    val companyName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    override val createdAt: String,
    override val createdBy: String?,
    override val lastUpdatedBy: String?,
    override val updatedAt: String
) : BaseModel()