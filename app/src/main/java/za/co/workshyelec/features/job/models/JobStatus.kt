package za.co.workshyelec.features.job.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import za.co.workshyelec.ui.theme.LocalStatusColors

enum class JobStatus {
    Pending,
    InProgress,
    OnHold,
    Completed,
    Failed,
    Cancelled,
    Verified,
    Billed,
    Paid,
    Closed
}

val JobStatus.color: Color
    @Composable
    get() = when (this) {
        JobStatus.Pending -> LocalStatusColors.current.pending
        JobStatus.InProgress -> LocalStatusColors.current.inProgress
        JobStatus.OnHold -> LocalStatusColors.current.onHold
        JobStatus.Completed -> LocalStatusColors.current.completed
        JobStatus.Failed -> LocalStatusColors.current.failed
        JobStatus.Cancelled -> LocalStatusColors.current.cancelled
        JobStatus.Verified -> LocalStatusColors.current.verified
        JobStatus.Billed -> LocalStatusColors.current.billed
        JobStatus.Paid -> LocalStatusColors.current.paid
        JobStatus.Closed -> LocalStatusColors.current.closed
    }