package za.co.workshyelec.composables.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import za.co.workshyelec.ui.theme.WorkshyAndroidUITheme

/**
 * A dynamic button composable that can be used both as a standalone action button and as part of a segmented control.
 * The appearance of the button (filled or outlined) can be determined by the [buttonType] or the [isSelected] state.
 *
 * @param text The text to be displayed on the button.
 * @param modifier The modifier to be applied to the button. Use [Modifier] to apply customizations like padding, size, etc.
 * @param buttonType Determines the default appearance of the button when not used as part of a segmented control. Can be either [ButtonType.Filled] or [ButtonType.Outlined].
 * @param cornerRadius The corner radius of the button shape. Use [AppShapes.smallCornerRadius] or other predefined shapes from [AppShapes] for consistency across the app.
 * @param color The color of the button. Use [MaterialTheme.colorScheme.primary] or other predefined colors from [MaterialTheme.colorScheme] for consistency across the app.
 * @param isSelected An optional parameter that, when provided, determines the appearance based on the selection state. When `true`, the button appears filled. When `false`, the button appears outlined. When `null`, the appearance is based on [buttonType].
 * @param onClick The lambda to be invoked when the button is clicked.
 *
 * Usage:
 * - As a standalone action button: Set [buttonType] to define its appearance and do not provide [isSelected].
 * - As part of a segmented control: Provide [isSelected] to dynamically switch the appearance based on the selection state.
 */
@Composable
fun WSDynamicButton(
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Filled,
    cornerRadius: Dp = AppShapes.smallCornerRadius,
    color: Color = MaterialTheme.colorScheme.primary,
    isSelected: Boolean? = null,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)

    // Determine container and content colors based on the button state and type
    val (containerColor, contentColor, border) = when {
        isSelected == true -> Triple(
            color, // Filled button color when selected
            MaterialTheme.colorScheme.onPrimary, // Text color for filled button
            null
        )

        isSelected == false -> Triple(
            Color.Transparent, // Transparent background for outlined button
            color, // Text color for outlined button
            BorderStroke(1.dp, color) // Border color for outlined button
        )

        buttonType == ButtonType.Filled -> Triple(
            color, // Filled button color
            MaterialTheme.colorScheme.onPrimary, // Text color for filled button
            null
        )

        else -> Triple(
            Color.Transparent, // Transparent background for outlined button
            color, // Text color for outlined button
            BorderStroke(1.dp, color) // Border color for outlined button
        )
    }
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = border
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}


@Preview(
    showBackground = true
)
@Composable
fun WKDynamicButtonPreview() {
    WorkshyAndroidUITheme {
        WSDynamicButton(
            text = "Button",
            buttonType = ButtonType.Filled,
        ) {
            // No button action
        }
    }
}