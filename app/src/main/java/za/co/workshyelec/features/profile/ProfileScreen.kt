package za.co.workshyelec.features.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import za.co.workshyelec.composables.WSBaseScreen

@Destination
@Composable
fun ProfileScreen(navController: NavController) {
    WSBaseScreen(navController = navController) {
        Text(text = "Profile Screen")
    }
}