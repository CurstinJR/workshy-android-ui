package za.co.workshyelec.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BaseScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    applyOuterPadding: Boolean = true,
    topBar: @Composable() (() -> Unit)? = null,
    bottomBar: @Composable() ((NavController) -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar ?: { PrimaryTopBar() },
        bottomBar = { bottomBar?.invoke(navController) ?: PrimaryBottomBar(navController) }
    ) { innerPadding ->
        if (applyOuterPadding) {
            Column(modifier = Modifier.padding(innerPadding)) {
                content(innerPadding)
            }
        } else {
            content(innerPadding)
        }
    }
}