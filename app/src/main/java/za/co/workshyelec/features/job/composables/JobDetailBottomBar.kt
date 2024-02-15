package za.co.workshyelec.features.job.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.workshyelec.composables.WSBaseBottomBar
import za.co.workshyelec.composables.button.AppShapes
import za.co.workshyelec.composables.button.ButtonType
import za.co.workshyelec.composables.button.WSDynamicButton

@Composable
fun JobDetailBottomBar() {
    WSBaseBottomBar(
        actions = {
            WSDynamicButton(
                text = "Directions",
                modifier = Modifier.weight(1f),
                buttonType = ButtonType.Outlined,
                cornerRadius = AppShapes.smallCornerRadius,
                onClick = { /*TODO*/ },
            )

            Spacer(modifier = Modifier.width(8.dp))

            WSDynamicButton(
                text = "Start Job",
                modifier = Modifier.weight(1f),
                buttonType = ButtonType.Filled,
                cornerRadius = AppShapes.smallCornerRadius,
                onClick = { /*TODO*/ },
            )
        }
    )
}