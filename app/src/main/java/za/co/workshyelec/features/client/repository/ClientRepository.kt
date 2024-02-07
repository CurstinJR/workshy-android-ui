package za.co.workshyelec.features.client.repository

import za.co.workshyelec.features.client.ClientApiClient

class ClientRepository(
    private val clientApiClient: ClientApiClient
) {
    suspend fun getClientList() = clientApiClient.getClientList()

    suspend fun getClientDetail(clientId: String) = clientApiClient.getClientDetail(clientId)
}