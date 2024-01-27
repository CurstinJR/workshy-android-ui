package za.co.workshyelec.core.common

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val itemsPerPage: Int,
    val totalItems: Int,
    val currentPage: Int,
    val nextPage: Int?,
    val prevPage: Int?
)

@Serializable
data class PaginatedResponse<T>(
    val pagination: Pagination,
    val items: MutableList<T>
)