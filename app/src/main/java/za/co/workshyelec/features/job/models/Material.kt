package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable

@Serializable
data class Material(
    val id: String,
    val materialId: String,
    val quantityUsed: String
)