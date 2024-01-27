package za.co.workshyelec.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import za.co.workshyelec.composables.BaseScreen

@Destination
@Composable
fun HomeScreen(navController: NavController) {
    BaseScreen(
        navController = navController,
    ) {
        Text(text = "Home Screen")
    }
}