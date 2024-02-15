package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable
import za.co.workshyelec.core.common.BaseModel

@Serializable
data class JobActivity(
    val description: String,
    val endTime: String,
    val id: String,
    val materials: List<Material>,
    val startTime: String,
    override val createdAt: String,
    override val createdBy: String?,
    override val lastUpdatedBy: String?,
    override val updatedAt: String
) : BaseModel()