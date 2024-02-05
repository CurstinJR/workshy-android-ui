package za.co.workshyelec.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.isSuccess
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import za.co.workshyelec.core.api.ApiErrorResponse
import za.co.workshyelec.core.api.ApiResponse

suspend inline fun <reified T> HttpClient.safeRequest(block: HttpRequestBuilder.() -> Unit): ApiResponse<T> =
    try {
        // 'this' refers to the HttpClient instance
        val response = request { block() }
        if (response.status.isSuccess()) {
            ApiResponse.Success(withContext(Dispatchers.IO) { response.body<T>() })
        } else {
            val errorResponse = withContext(Dispatchers.IO) {
                try {
                    response.body<ApiErrorResponse>()
                } catch (e: SerializationException) {
                    ApiErrorResponse(response.status.value, "Error parsing error response")
                }
            }
            ApiResponse.Error(errorResponse)
        }
    } catch (e: ClientRequestException) {
        ApiResponse.Error(handleClientError(e))
    } catch (e: ServerResponseException) {
        ApiResponse.Error(handleServerError(e))
    } catch (e: IOException) {
        ApiResponse.Error(ApiErrorResponse(-1, "Network error"))
    } catch (e: SerializationException) {
        ApiResponse.Error(ApiErrorResponse(-1, "Serialization error"))
    }

suspend fun handleClientError(e: ClientRequestException): ApiErrorResponse =
    withContext(Dispatchers.IO) {
        try {
            e.response.body<ApiErrorResponse>()
        } catch (serializationException: SerializationException) {
            ApiErrorResponse(e.response.status.value, "Client error")
        }
    }

suspend fun handleServerError(e: ServerResponseException): ApiErrorResponse =
    withContext(Dispatchers.IO) {
        try {
            e.response.body<ApiErrorResponse>()
        } catch (serializationException: SerializationException) {
            ApiErrorResponse(e.response.status.value, "Server error")
        }
    }