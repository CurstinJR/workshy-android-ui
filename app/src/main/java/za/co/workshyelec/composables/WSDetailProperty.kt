package za.co.workshyelec.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun WSDetailProperty(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    contentStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    contentColor: Color? = null
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = titleStyle,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = content,
            style = contentStyle,
            color = contentColor ?: MaterialTheme.colorScheme.onSurface
        )
    }
}