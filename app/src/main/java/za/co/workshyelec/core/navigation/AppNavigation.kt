package za.co.workshyelec.core.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object AppNavigation {
    private val _globalNavigationEvents = MutableSharedFlow<NavigationEvent>()
    val globalNavigationEvents: SharedFlow<NavigationEvent> = _globalNavigationEvents

    suspend fun emit(event: NavigationEvent) {
        _globalNavigationEvents.emit(event)
    }
}