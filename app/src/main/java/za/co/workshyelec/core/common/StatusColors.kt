package za.co.workshyelec.core.common

import androidx.compose.ui.graphics.Color

data class StatusColors(
    val pending: Color = Color.Unspecified,
    val inProgress: Color = Color.Unspecified,
    val onHold: Color = Color.Unspecified,
    val completed: Color = Color.Unspecified,
    val failed: Color = Color.Unspecified,
    val cancelled: Color = Color.Unspecified,
    val verified: Color = Color.Unspecified,
    val billed: Color = Color.Unspecified,
    val paid: Color = Color.Unspecified,
    val closed: Color = Color.Unspecified
) {
    fun colorForStatus(status: String): Color {
        return when (status) {
            "Pending" -> pending
            "In Progress" -> inProgress
            "On Hold" -> onHold
            "Completed" -> completed
            "Failed" -> failed
            "Cancelled" -> cancelled
            "Verified" -> verified
            "Billed" -> billed
            "Paid" -> paid
            "Closed" -> closed
            else -> Color.Unspecified
        }
    }
}
