package za.co.workshyelec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import za.co.workshyelec.core.auth.UserSessionManager
import za.co.workshyelec.features.NavGraphs
import za.co.workshyelec.features.destinations.HomeScreenDestination
import za.co.workshyelec.features.destinations.LoginScreenDestination
import za.co.workshyelec.ui.theme.WorkshyAndroidUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userSessionManager = UserSessionManager(this)
        val startDestination =
            if (userSessionManager.isLoggedIn()) HomeScreenDestination else LoginScreenDestination

        setContent {
            val navController = rememberNavController()

            WorkshyAndroidUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        startRoute = startDestination
                    )
                }
            }
        }
    }
}
