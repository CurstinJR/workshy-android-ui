package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.models.JobStatus
import za.co.workshyelec.features.job.models.color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(
    job: Job,
    onJobClick: () -> Unit
) {
    val jobStatus = JobStatus.valueOf(job.status)

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onJobClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            JobCardHeader(
                status = job.status,
                formattedDate = job.formattedScheduledDate,
                statusColor = jobStatus.color,
                dateColor = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = job.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = job.location,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun JobCardHeader(
    status: String,
    formattedDate: String,
    statusColor: Color,
    dateColor: Color,
    style: TextStyle
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = status,
            style = style,
            color = statusColor
        )

        Text(
            text = formattedDate,
            style = style,
            color = dateColor
        )
    }
}