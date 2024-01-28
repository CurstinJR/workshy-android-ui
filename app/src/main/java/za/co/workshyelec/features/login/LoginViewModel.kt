package za.co.workshyelec.features.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import za.co.workshyelec.core.auth.AuthApiClient
import za.co.workshyelec.core.auth.UserSessionManager
import za.co.workshyelec.core.common.BaseViewModel
import za.co.workshyelec.core.navigation.NavigationEvent
import za.co.workshyelec.features.destinations.HomeScreenDestination

class LoginViewModel(
    private val authApiClient: AuthApiClient,
    private val userSessionManager: UserSessionManager
) : BaseViewModel() {
    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val username: StateFlow<String> = _username.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()

    // Validation logic as StateFlow
    val isFormValid = combine(_username, _password) { username, password ->
        username.isNotBlank() && password.isNotBlank()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    // Update functions for username and password
    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            authApiClient.login(_username.value, _password.value)
            userSessionManager.setLoggedIn(true)
            emitNavigationEvent(NavigationEvent.NavigateTo(HomeScreenDestination))
        }
    }
}