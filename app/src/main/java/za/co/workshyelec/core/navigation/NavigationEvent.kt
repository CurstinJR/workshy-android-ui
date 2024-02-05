package za.co.workshyelec.core.navigation

import com.ramcosta.composedestinations.spec.Direction

sealed class NavigationEvent {
    data class NavigateTo(val direction: Direction) : NavigationEvent()
    data object GoBack : NavigationEvent()
    data class CurrentDestination(val destination: String) : NavigationEvent()
}