package za.co.workshyelec.features.job

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.core.navigation.NavigationHandlerImpl
import za.co.workshyelec.features.job.jobList.JobListScreen

@Destination
@Composable
fun JobScreen(navController: NavController) {
    val navigationHandler = remember { NavigationHandlerImpl(navController) }

    BaseScreen(navController = navController) {
        JobListScreen(navigationHandler = navigationHandler)
    }
}