package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import za.co.workshyelec.features.job.models.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(job: Job, onJobClick: (String) -> Unit) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onJobClick(job.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = job.name)
            Text(text = job.status)
            Text(text = job.scheduledDate)
            Text(text = job.location)
        }
    }
}