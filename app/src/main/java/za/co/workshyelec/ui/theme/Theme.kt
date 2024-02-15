package za.co.workshyelec.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import za.co.workshyelec.core.common.StatusColors

private val DarkColorScheme = darkColorScheme(
    primary = DarkBluePrimary,
    onPrimary = OnDarkPrimary,
    primaryContainer = DarkBluePrimaryContainer,
    onPrimaryContainer = OnDarkPrimary,
    secondary = DarkAmberSecondary,
    onSecondary = OnDarkSecondary,
    secondaryContainer = DarkAmberSecondaryContainer,
    onSecondaryContainer = OnDarkSecondary,
    tertiary = DarkTealTertiary,
    onTertiary = OnDarkTertiary,
    tertiaryContainer = DarkTealTertiaryContainer,
    onTertiaryContainer = OnDarkTertiary,
    background = DarkBackground,
    onBackground = OnDarkBackground,
    surface = DarkSurface,
    onSurface = OnDarkSurface,
    error = DarkError,
    onError = OnDarkError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = OnDarkError,
    outline = DarkOutline,
    inverseSurface = DarkInverseSurface,
    inverseOnSurface = DarkInverseOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = OnDarkSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = OnPrimary,
    primaryContainer = BluePrimaryContainer,
    onPrimaryContainer = OnPrimary,
    secondary = AmberSecondary,
    onSecondary = OnSecondary,
    secondaryContainer = AmberSecondaryContainer,
    onSecondaryContainer = OnSecondary,
    tertiary = TealTertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TealTertiaryContainer,
    onTertiaryContainer = OnTertiary,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnError,
    outline = Outline,
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant
)

val LocalStatusColors = staticCompositionLocalOf { StatusColors() }

@Composable
fun WorkshyAndroidUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val statusColors = if (darkTheme) {
        StatusColors(
            pending = DarkStatusPending,
            inProgress = DarkStatusInProgress,
            onHold = DarkStatusOnHold,
            completed = DarkStatusCompleted,
            failed = DarkStatusFailed,
            cancelled = DarkStatusCancelled,
            verified = DarkStatusVerified,
            billed = DarkStatusBilled,
            paid = DarkStatusPaid,
            closed = DarkStatusClosed
        )
    } else {
        StatusColors(
            pending = StatusPending,
            inProgress = StatusInProgress,
            onHold = StatusOnHold,
            completed = StatusCompleted,
            failed = StatusFailed,
            cancelled = StatusCancelled,
            verified = StatusVerified,
            billed = StatusBilled,
            paid = StatusPaid,
            closed = StatusClosed
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(LocalStatusColors provides statusColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}