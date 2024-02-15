package za.co.workshyelec.composables

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
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
import za.co.workshyelec.features.destinations.ProfileScreenDestination
import za.co.workshyelec.features.startAppDestination

enum class PrimaryBottomBarDestination(
    val direction: DirectionDestinationSpec,
    val iconResourceId: Int,
    @StringRes val label: Int
) {
    Home(
        direction = HomeScreenDestination,
        iconResourceId = R.drawable.dashboard,
        label = R.string.home_label
    ),
    Job(
        direction = JobScreenDestination,
        iconResourceId = R.drawable.assignment,
        label = R.string.jobs_label
    ),
    Profile(
        direction = ProfileScreenDestination,
        iconResourceId = R.drawable.account_circle,
        label = R.string.profile_label
    )
}

@Composable
fun WSPrimaryBottomBar(
    navController: NavController
) {
    val currentDestination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    WSBaseBottomBar(
        actions = {
            PrimaryBottomBarDestination.entries.forEach { destination ->
                val icon = painterResource(id = destination.iconResourceId)

                NavigationBarItem(
                    selected = currentDestination == destination.direction,
                    onClick = {
                        if (currentDestination.route != destination.direction.route) {
                            navController.navigate(destination.direction) {
                                restoreState = true
                                launchSingleTop = true
                                popUpTo(NavGraphs.root.startDestination) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = icon,
                            contentDescription = stringResource(destination.label),
                        )
                    },
                    label = {
                        Text(stringResource(destination.label))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            LocalAbsoluteTonalElevation.current
                        ),
                    )
                )
            }
        }
    )
}