package za.co.workshyelec.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.navigation.NavigationEvent

open class BaseViewModel : ViewModel() {
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent.asSharedFlow()

    protected fun emitNavigationEvent(event: NavigationEvent) {
        viewModelScope.launch {
            _navigationEvent.emit(event)
        }
    }
}