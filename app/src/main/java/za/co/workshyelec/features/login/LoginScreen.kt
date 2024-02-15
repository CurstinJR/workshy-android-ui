package za.co.workshyelec.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import org.koin.androidx.compose.koinViewModel
import za.co.workshyelec.core.navigation.AppNavigation
import za.co.workshyelec.core.navigation.NavigationHandlerImpl

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel(),
    navController: NavController,
) {
    // Initialize NavigationHandler with NavController
    val navigationHandler = remember { NavigationHandlerImpl(navController) }

    val username by loginViewModel.username.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val loginError by loginViewModel.loginError.collectAsState()
    val isFormValid by loginViewModel.isFormValid.collectAsState()

    // Collect navigation events from the ViewModel
    LaunchedEffect(loginViewModel) {
        loginViewModel.navigationEvent.collect { event ->
            navigationHandler.handleNavigationEvent(event)
        }
    }

    // Collect global navigation events
    LaunchedEffect(Unit) {
        AppNavigation.globalNavigationEvents.collect { event ->
            navigationHandler.handleNavigationEvent(event)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TextField(
                value = username,
                onValueChange = loginViewModel::updateUsername,
                label = { Text("Username") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = loginViewModel::updatePassword,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            // Display the error message if the login failed
            if (loginError.isNotBlank()) {
                Text(
                    text = loginError,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { loginViewModel.login() },
                enabled = isFormValid
            ) {
                Text("Login")
            }
        }
    }
}