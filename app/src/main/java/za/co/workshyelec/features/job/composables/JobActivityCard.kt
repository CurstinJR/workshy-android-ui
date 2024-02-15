package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import za.co.workshyelec.features.job.models.JobActivity

@Composable
fun JobActivityCard(
    jobActivity: JobActivity,
    onJobActivityClick: () -> Unit = {}
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onJobActivityClick)
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            JobActivityHeader(
                createdBy = jobActivity.createdBy,
                createdAt = jobActivity.formattedCreatedAt
            )

            Text(
                text = jobActivity.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )

            ShowMoreButton(onClick = onJobActivityClick)
        }
    }
}

@Composable
private fun JobActivityHeader(
    createdBy: String?,
    createdAt: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Canvas(modifier = Modifier.size(70.dp)) {
            drawCircle(color = Color.LightGray)
        }

        Column {
            Text(
                text = createdBy ?: "Unknown",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = createdAt,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}

@Composable
private fun ShowMoreButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "Show more",
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Show more details",
            modifier = Modifier
                .size(17.dp)
                .padding(top = 1.dp)
        )
    }
}
