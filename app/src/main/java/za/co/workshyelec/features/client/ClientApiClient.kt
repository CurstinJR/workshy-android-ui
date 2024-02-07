package za.co.workshyelec.features.client

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.ktor.safeRequest
import za.co.workshyelec.features.client.model.Client

class ClientApiClient(
    private val httpClient: HttpClient
) {

    suspend fun getClientList(): ApiResponse<List<Client>> = httpClient.safeRequest {
        url("/api/clients")
        method = HttpMethod.Get
    }

    suspend fun getClientDetail(clientId: String): ApiResponse<Client> = httpClient.safeRequest {
        url("/api/clients/$clientId")
        method = HttpMethod.Get
    }
}