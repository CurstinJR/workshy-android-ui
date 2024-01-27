package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable

@Serializable
data class Job(
    val activities: List<String>,
    val client: String,
    val completedDate: String,
    val createdAt: String,
    val createdBy: String?,
    val description: String,
    val employees: List<String>,
    val id: String,
    val images: List<String>,
    val lastUpdatedBy: String?,
    val leadEmployee: String,
    val location: String,
    val name: String,
    val notes: String,
    val scheduledDate: String,
    val signOffDate: String,
    val signedOffBy: String,
    val status: String,
    val type: String,
    val updatedAt: String
)