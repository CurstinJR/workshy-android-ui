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
        navController.navigate(direction.route)
    }

    /**
     * Navigate back in the application.
     */
    override fun goBack() {
        navController.navigateUp()
    }
}