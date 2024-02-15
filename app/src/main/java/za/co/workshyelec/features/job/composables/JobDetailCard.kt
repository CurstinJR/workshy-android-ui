package za.co.workshyelec.features.job.composables

import androidx.compose.runtime.Composable
import za.co.workshyelec.composables.WSDetailCard
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.ui.theme.LocalStatusColors

@Composable
fun JobDetailCard(job: Job) {
    val jobStatusColor = LocalStatusColors.current.colorForStatus(job.status)

    val jobDetails = listOf(
        "Status" to job.status,
        "Time" to job.formattedScheduledDate,
        "ID" to job.id,
        "Type" to job.type,
        "Description" to job.description
    )

    // Job Detail Card
    WSDetailCard(
        header = "Work Detail",
        details = jobDetails,
        statusColor = jobStatusColor
    )
}