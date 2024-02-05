package za.co.workshyelec.core.auth

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.cookie
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.auth.models.LoginResponse
import za.co.workshyelec.core.auth.models.UserCredentials
import za.co.workshyelec.core.ktor.safeRequest
import za.co.workshyelec.core.navigation.AppNavigation
import za.co.workshyelec.core.navigation.NavigationEvent
import za.co.workshyelec.features.destinations.LoginScreenDestination

class AuthApiClient(
    private val httpClient: HttpClient,
    private val userSessionManager: UserSessionManager
) {
    suspend fun login(username: String, password: String): ApiResponse<LoginResponse> {
        val response: ApiResponse<LoginResponse> = httpClient.safeRequest {
            url("/api/auth/login")
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(UserCredentials(username, password))
        }

        if (response is ApiResponse.Success) {
            userSessionManager.setAccessToken(response.data.accessToken)
            userSessionManager.setRefreshToken(response.data.refreshToken)
        }

        return response
    }

    suspend fun refreshAccessToken(): BearerTokens? {
        val refreshToken = userSessionManager.getRefreshToken()

        // Check if the refresh token is available
        if (refreshToken.isNullOrBlank()) {
            AppNavigation.emit(NavigationEvent.NavigateTo(LoginScreenDestination))
            return null
        }

        // Make a request to the refresh token endpoint
        val response: ApiResponse<LoginResponse> = httpClient.safeRequest {
            url("/api/auth/refresh")
            method = HttpMethod.Post
            cookie("refreshToken", refreshToken)
        }


        return when (response) {
            is ApiResponse.Success -> {
                // Update the session manager with the new tokens
                userSessionManager.setAccessToken(response.data.accessToken)
                userSessionManager.setRefreshToken(response.data.refreshToken)
                BearerTokens(response.data.accessToken, response.data.refreshToken)
            }

            else -> {
                // If the refresh token request fails, navigate to the login screen
                AppNavigation.emit(NavigationEvent.NavigateTo(LoginScreenDestination))
                null
            }
        }
    }
}