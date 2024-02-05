package za.co.workshyelec.core.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.Direction

/**
 * Interface for handling navigation in the application.
 */
interface NavigationHandler {
    /**
     * Navigate to a specific direction in the application.
     * @param direction The direction to navigate to.
     */
    fun navigateTo(direction: Direction)

    /**
     * Navigate back in the application.
     */
    fun goBack()

    /**
     * Get the current destination of the application.
     * @return The current destination of the application.
     */
    fun currentDestination(): String?
}

/**
 * Implementation of the NavigationHandler interface.
 * Uses an instance of DestinationsNavigator to handle the navigation.
 *
 * @property navController An instance of NavController.
 */
class NavigationHandlerImpl(
    private val navController: NavController
) : NavigationHandler {
    /**
     * Navigate to a specific direction in the application.
     * @param direction The direction to navigate to.
     */
    override fun navigateTo(direction: Direction) {
        if (currentDestination() != direction.route) {
            navController.navigate(direction.route)
        }
    }

    /**
     * Navigate back in the application.
     */
    override fun goBack() {
        navController.navigateUp()
    }

    /**
     * Get the current destination of the application.
     * This function uses the NavController to retrieve the current destination's route.
     * The route is a string that represents the unique identifier of a destination in the navigation graph.
     *
     * @return The route of the current destination. If there is no current destination, it returns null.
     */
    override fun currentDestination(): String? {
        return navController.currentDestination?.route
    }

    fun handleNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.NavigateTo -> navigateTo(event.direction)
            is NavigationEvent.GoBack -> goBack()
            is NavigationEvent.CurrentDestination -> {}
        }
    }
}