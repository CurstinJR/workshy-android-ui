package za.co.workshyelec.core.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException
import za.co.workshyelec.core.auth.models.LoginDetails
import za.co.workshyelec.core.auth.models.LoginResponse

class AuthApiClient(
    private val httpClient: HttpClient,
    private val userSessionManager: UserSessionManager
) {
    suspend fun login(username: String, password: String): LoginResponse? {
        return try {
            val loginResponse = httpClient.post("/api/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginDetails(username, password))
            }.body<LoginResponse>()

            userSessionManager.setAccessToken(loginResponse.accessToken)
            userSessionManager.setRefreshToken(loginResponse.refreshToken)

            loginResponse
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun refreshAccessToken(): BearerTokens? {
        val refreshToken = userSessionManager.getRefreshToken()

        // Check if the refresh token is available
        if (refreshToken.isNullOrBlank()) {
            return null
        }

        return try {
            // Make a request to the refresh token endpoint
            val response: LoginResponse = httpClient.post("/api/auth/refresh").body()

            // Update the session manager with the new tokens
            userSessionManager.setAccessToken(response.accessToken)
            userSessionManager.setRefreshToken(response.refreshToken)

            // Return the new tokens
            BearerTokens(response.accessToken, response.refreshToken)
        } catch (e: Exception) {
            null
        }
    }
}