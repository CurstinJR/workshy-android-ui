package za.co.workshyelec.core.api

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

object ApiErrorUtils {
    suspend fun parseErrorResponse(throwable: Throwable): ApiErrorResponse {
        return when (throwable) {
            is ResponseException -> {
                val response = throwable.response
                val statusCode = response.status.value
                val responseBody = response.bodyAsText()

                try {
                    val jsonElement = Json.parseToJsonElement(responseBody)
                    if (jsonElement is JsonObject) {
                        val message =
                            jsonElement["message"]?.jsonPrimitive?.content ?: "Unknown error"
                        val timestamp = jsonElement["timestamp"]?.jsonPrimitive?.content ?: ""
                        val path = jsonElement["path"]?.jsonPrimitive?.content ?: ""

                        ApiErrorResponse(statusCode, message, timestamp, path)
                    } else {
                        ApiErrorResponse(statusCode, "Unknown error", "", "")
                    }
                } catch (e: Exception) {
                    // Handle JSON parsing exceptions
                    ApiErrorResponse(statusCode, "Error occurred", "", "")
                }
            }

            else -> {
                // For non-HTTP exceptions
                ApiErrorResponse(-1, throwable.message ?: "Unknown error", "", "")
            }
        }
    }
}