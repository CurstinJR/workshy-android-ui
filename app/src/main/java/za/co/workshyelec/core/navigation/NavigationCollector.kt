package za.co.workshyelec.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavigationCollector(navigationHandler: NavigationHandler) {
    LaunchedEffect(Unit) {
        AppNavigation.globalNavigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> navigationHandler.navigateTo(event.direction)
                is NavigationEvent.GoBack -> navigationHandler.goBack()
            }
        }
    }
}