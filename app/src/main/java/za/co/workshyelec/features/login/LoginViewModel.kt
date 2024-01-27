package za.co.workshyelec.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import za.co.workshyelec.core.auth.AuthApiClient
import za.co.workshyelec.core.auth.UserSessionManager
import za.co.workshyelec.features.destinations.DirectionDestination
import za.co.workshyelec.features.destinations.HomeScreenDestination

class LoginViewModel(
    private val authApiClient: AuthApiClient,
    private val userSessionManager: UserSessionManager
) : ViewModel() {
    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _navigationEvent = MutableSharedFlow<DirectionDestination>()

    val username: StateFlow<String> = _username.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val navigationEvent = _navigationEvent.asSharedFlow()

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

    suspend fun login() {
        authApiClient.login(_username.value, _password.value)
        userSessionManager.setLoggedIn(true)
        _navigationEvent.emit(HomeScreenDestination)
    }
}