package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable
import za.co.workshyelec.core.common.BaseModel

@Serializable
data class JobType(
    val id: String,
    val name: String,
    val description: String,
    override val createdAt: String,
    override val createdBy: String?,
    override val lastUpdatedBy: String?,
    override val updatedAt: String
) : BaseModel()