package za.co.workshyelec.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.ConstantCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.Cookie
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import za.co.workshyelec.core.auth.UserSessionManager

fun createHttpClient(
    userSessionManager: UserSessionManager,
    onTokenRefresh: suspend () -> BearerTokens?
): HttpClient {
    return HttpClient {
        expectSuccess = true

        defaultRequest {
            host = "10.0.2.2"
            port = 3000
            url { protocol = URLProtocol.HTTP }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(HttpCookies) {
            storage = ConstantCookiesStorage(
                Cookie(
                    name = "refreshToken",
                    value = userSessionManager.getRefreshToken() ?: "",
                    domain = "10.0.2.2"
                )
            )
        }

        install(HttpTimeout)

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val accessToken = userSessionManager.getAccessToken() ?: ""
                    val refreshToken = userSessionManager.getRefreshToken() ?: ""

                    BearerTokens(accessToken, refreshToken)
                }

                refreshTokens {
                    try {
                        onTokenRefresh() ?: throw Exception("Token refresh failed")
                    } catch (e: Exception) {
                        // Handle exception or log error
                        null
                    }
                }
            }
        }
    }
}