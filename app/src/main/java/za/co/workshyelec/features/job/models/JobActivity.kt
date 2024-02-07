package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable

@Serializable
data class JobActivity(
    val createdAt: String,
    val createdBy: String?,
    val description: String,
    val endTime: String,
    val id: String,
    val lastUpdatedBy: String?,
    val materials: List<Material>,
    val startTime: String,
    val updatedAt: String
)