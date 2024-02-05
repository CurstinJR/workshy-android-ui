package za.co.workshyelec.core.api

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: ApiErrorResponse) : ApiResponse<Nothing>()
}