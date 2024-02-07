package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import za.co.workshyelec.features.client.model.Client

@Composable
fun ClientDetailCard(client: Client) {
    Text(
        text = "Client Detail",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
    )

    Spacer(modifier = Modifier.padding(4.dp))

    // Job Detail Card
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailProperty(title = "Company Name", content = client.companyName)

                DetailProperty(title = "Email", content = client.email)
            }

            Spacer(modifier = Modifier.padding(8.dp))

            DetailProperty(title = "Address", content = client.address)

            Spacer(modifier = Modifier.padding(8.dp))

            DetailProperty(title = "Phone Number", content = client.phoneNumber)

            Spacer(modifier = Modifier.padding(8.dp))

            DetailProperty(title = "Client Type", content = client.clientType)
        }
    }
}