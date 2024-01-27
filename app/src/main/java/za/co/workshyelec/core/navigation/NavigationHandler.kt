package za.co.workshyelec.core.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

interface NavigationHandler {
    fun navigateTo(direction: Direction)
}

class NavigationHandlerImpl(
    private val navigator: DestinationsNavigator
) : NavigationHandler {
    override fun navigateTo(direction: Direction) {
        navigator.navigate(direction)
    }
}