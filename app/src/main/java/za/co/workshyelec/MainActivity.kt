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
import org.koin.android.ext.android.getKoin
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.core.auth.UserSessionManager
import za.co.workshyelec.core.navigation.NavigationCollector
import za.co.workshyelec.core.navigation.NavigationHandler
import za.co.workshyelec.core.navigation.NavigationModule
import za.co.workshyelec.features.NavGraphs
import za.co.workshyelec.features.destinations.HomeScreenDestination
import za.co.workshyelec.features.destinations.LoginScreenDestination
import za.co.workshyelec.ui.theme.WorkshyAndroidUITheme

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userSessionManager = UserSessionManager(this)
        val startDestination =
            if (userSessionManager.isLoggedIn()) HomeScreenDestination else LoginScreenDestination

        setContent {
            rememberKoinModules(unloadOnForgotten = true) {
                listOf(NavigationModule)
            }

            val navController = rememberNavController()
            val navigationHandler: NavigationHandler = getKoin().get { parametersOf(navController) }

            NavigationCollector(navigationHandler)

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
