package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.co.workshyelec.features.job.models.JobActivity

@Composable
fun JobActivityCard(
    jobActivity: JobActivity,
    onJobActivityClick: () -> Unit = {}
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Image Placeholder
                Canvas(modifier = Modifier.size(70.dp)) {
                    drawCircle(color = Color.LightGray)
                }

                // Name and Time
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = jobActivity.createdBy ?: "Unknown",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = jobActivity.createdAt,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            // Description
            Text(
                text = jobActivity.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Show more button
            TextButton(
                onClick = { onJobActivityClick() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Show more",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow Forward",
                        modifier = Modifier
                            .size(17.dp)
                            .padding(top = 1.dp),
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun JobActivityCardPreview() {
    JobActivityCard(
        JobActivity(
            "10 May 10:45 AM",
            "John Doe",
            "Job activity description",
            "2021-10-01T12:00:00Z",
            "1",
            "John Doe",
            emptyList(),
            "2021-10-01T12:00:00Z",
            "2021-10-01T12:00:00Z"
        )
    )
}