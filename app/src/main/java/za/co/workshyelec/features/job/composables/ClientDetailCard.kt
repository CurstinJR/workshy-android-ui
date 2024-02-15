package za.co.workshyelec.features.job.composables

import androidx.compose.runtime.Composable
import za.co.workshyelec.composables.WSDetailCard
import za.co.workshyelec.features.client.model.Client

@Composable
fun ClientDetailCard(client: Client) {
    val clientDetails = listOf(
        "Company Name" to client.companyName,
        "Email" to client.email,
        "Address" to client.address,
        "Phone Number" to client.phoneNumber,
        "Client Type" to client.clientType
    )

    WSDetailCard(
        header = "Client Detail",
        details = clientDetails
    )
}