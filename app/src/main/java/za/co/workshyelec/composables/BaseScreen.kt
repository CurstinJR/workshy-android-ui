package za.co.workshyelec.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BaseScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable ((NavController) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            val bar = topBar ?: { PrimaryTopBar() }
            bar()
        },
        bottomBar = {
            val bar =
                bottomBar ?: { navController -> PrimaryBottomBar(navController = navController) }
            bar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            content()
        }
    }
}