package za.co.workshyelec.features.job.jobDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.core.common.handle
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
    val state by jobDetailViewModel.jobDetail.collectAsState()

    BaseScreen(
        navController = navController,
        topBar = { JobDetailTopBar() },
        bottomBar = { JobDetailBottomBar() }
    ) {
        Text(text = "Job Detail Screen")
        state.handle(
            onSuccess = { jobDetail ->
                Text(text = jobDetail.name)
            },
            onError = { error, _ ->
                Text(text = error.message)
            },
            onEmpty = {
                Text(text = "No job detail")
            }
        )
    }
}