package za.co.workshyelec.features.job.models

import kotlinx.serialization.Serializable
import za.co.workshyelec.core.common.BaseModel

@Serializable
data class Employee(
    val dateHired: String,
    val firstName: String,
    val hourlyRate: Int,
    val id: String,
    val lastName: String,
    val position: String,
    val user: String?,
    override val createdAt: String,
    override val createdBy: String?,
    override val lastUpdatedBy: String?,
    override val updatedAt: String
) : BaseModel()