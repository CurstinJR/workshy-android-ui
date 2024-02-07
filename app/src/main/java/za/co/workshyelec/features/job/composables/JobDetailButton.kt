package za.co.workshyelec.features.job.composables

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun JobDetailButton(
    label: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = label)
    }
}