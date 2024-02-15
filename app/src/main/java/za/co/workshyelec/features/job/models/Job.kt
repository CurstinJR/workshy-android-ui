package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable
import za.co.workshyelec.core.common.BaseModel

@Serializable
data class Job(
    val activities: List<String>,
    val client: String,
    val completedDate: String,
    val description: String,
    val employees: List<String>,
    val id: String,
    val images: List<String>,
    val leadEmployee: String,
    val location: String,
    val name: String,
    val notes: String,
    val scheduledDate: String,
    val signOffDate: String,
    val signedOffBy: String,
    val status: String,
    val type: String,
    override val createdAt: String,
    override val createdBy: String?,
    override val lastUpdatedBy: String?,
    override val updatedAt: String
) : BaseModel() {
    val formattedScheduledDate: String
        get() = formatTime(scheduledDate)

    val formattedCompletedDate: String
        get() = formatTime(completedDate)

    val formattedSignOffDate: String
        get() = formatTime(signOffDate)
}
