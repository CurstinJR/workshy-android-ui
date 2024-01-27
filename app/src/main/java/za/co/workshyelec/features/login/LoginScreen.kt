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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import za.co.workshyelec.core.navigation.NavigationHandlerImpl

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {
    val username by loginViewModel.username.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val isFormValid by loginViewModel.isFormValid.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val navigationHandler = remember { NavigationHandlerImpl(navigator) }

    // LaunchedEffect is a Composable that launches a suspend function (effect)
    // when the provided keys change. In this case, the key is the loginViewModel.
    LaunchedEffect(loginViewModel) {
        // The collect function is called on the navigationEvent Flow from the loginViewModel.
        // This function collects each value emitted by the Flow and applies the given action to it.
        // In this case, the action is navigating to the directionDestination.
        loginViewModel.navigationEvent.collect { directionDestination ->
            navigationHandler.navigateTo(directionDestination)
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch { loginViewModel.login() }
                },
                enabled = isFormValid
            ) {
                Text("Login")
            }
        }
    }
}