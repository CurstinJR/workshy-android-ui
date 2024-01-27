package za.co.workshyelec.composables

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.startDestination
import za.co.workshyelec.R
import za.co.workshyelec.features.NavGraphs
import za.co.workshyelec.features.appCurrentDestinationAsState
import za.co.workshyelec.features.destinations.HomeScreenDestination
import za.co.workshyelec.features.destinations.JobScreenDestination
import za.co.workshyelec.features.startAppDestination

enum class PrimaryBottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(
        direction = HomeScreenDestination,
        icon = Icons.Default.Home,
        label = R.string.home_label
    ),
    Job(
        direction = JobScreenDestination,
        icon = Icons.Default.List,
        label = R.string.jobs_label
    ),
}

@Composable
fun PrimaryBottomBar(
    navController: NavController
) {
    val currentDestination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    BottomAppBar {
        PrimaryBottomBarDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    // Checks if the current destination route is not the same as the destination direction route
                    if (currentDestination.route != destination.direction.route) {
                        // If they are not the same, navigate to the destination direction
                        navController.navigate(destination.direction) {
                            // Restores the previous state of the destination
                            restoreState = true
                            // Ensures that the destination is launched in the same task as the source of the navigation
                            launchSingleTop = true
                            // Pops up to the start destination of the root navigation graph, saving its state
                            popUpTo(NavGraphs.root.startDestination) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = {
                    Text(stringResource(destination.label))
                }
            )
        }
    }
}