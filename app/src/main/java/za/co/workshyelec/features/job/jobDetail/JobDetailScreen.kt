package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.composables.JobDetailBottomBar
import za.co.workshyelec.features.job.composables.JobDetailTopBar

data class JobDetailScreenArgs(
    val jobId: String
)

@Destination(
    navArgsDelegate = JobDetailScreenArgs::class
)
@Composable
fun JobDetailScreen(
    navController: NavController,
    jobDetailScreenArgs: JobDetailScreenArgs,
    jobDetailViewModel: JobDetailViewModel = koinViewModel { parametersOf(jobDetailScreenArgs.jobId) }
) {
    val jobDetailState by jobDetailViewModel.jobDetail.collectAsState()

    BaseScreen(
        navController = navController,
        topBar = { JobDetailTopBar() },
        bottomBar = { JobDetailBottomBar() }
    ) {
        Text(text = "Job Detail Screen")

        when (val state = jobDetailState) {
            is UiState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                Text(text = state.data.name)
            }

            is UiState.Error -> {
                Text(text = state.errorResponse.message)
            }

            else -> Text(text = "No job detail")
        }
    }
}