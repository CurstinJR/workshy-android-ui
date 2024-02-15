package za.co.workshyelec.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun WSDetailCard(
    header: String,
    details: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
    statusColor: Color? = null,
    headerStyle: TextStyle = MaterialTheme.typography.titleMedium,
    itemTitleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    itemContentStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    outlineColor: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = header,
            style = headerStyle
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedCard(
            colors = CardDefaults.cardColors(containerColor = containerColor),
            border = BorderStroke(1.dp, outlineColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                details.forEachIndexed { index, (title, content) ->
                    if (index > 0) Spacer(modifier = Modifier.height(8.dp))
                    val isStatus = title == "Status"
                    WSDetailProperty(
                        title = title,
                        content = content,
                        titleStyle = itemTitleStyle,
                        contentStyle = itemContentStyle,
                        contentColor = if (isStatus) statusColor else null
                    )
                }
            }
        }
    }
}