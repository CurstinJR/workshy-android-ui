package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.composables.CircularLoadingIndicator
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.common.handle
import za.co.workshyelec.core.navigation.NavigationHandlerImpl
import za.co.workshyelec.features.job.composables.ClientDetailCard
import za.co.workshyelec.features.job.composables.JobDetailBottomBar
import za.co.workshyelec.features.job.composables.JobDetailButton
import za.co.workshyelec.features.job.composables.JobDetailCard
import za.co.workshyelec.features.job.composables.JobDetailTopBar
import za.co.workshyelec.features.job.models.Job

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
    val navigationHandler = remember { NavigationHandlerImpl(navController) }

    val currentViewState by jobDetailViewModel.currentView.collectAsState()
    // Observe the consolidated screen state
    val screenState by jobDetailViewModel.screenState.collectAsState()

    BaseScreen(
        navController = navController,
        topBar = {
            JobDetailTopBar(
                jobId = jobDetailScreenArgs.jobId,
                onBackClick = { navigationHandler.goBack() }
            )
        },
        bottomBar = { JobDetailBottomBar() }
    ) {
        JobDetailScreenContent(
            state = screenState.jobDetail,
            onDetailSelected = { view ->
                jobDetailViewModel.setCurrentView(view)
            }
        )

        when (currentViewState) {
            JobDetailView.Detail -> {
                DetailView(state = screenState)
            }

            JobDetailView.Activity -> {
                JobActivityListView(activityListState = screenState.activityList)
            }

            else -> {}
        }
    }
}

@Composable
private fun JobDetailScreenContent(
    state: UiState<Job>,
    onDetailSelected: (JobDetailView) -> Unit
) {
    val scrollState = rememberScrollState()
    val views = listOf(JobDetailView.Detail, JobDetailView.Activity)

    state.handle(
        onNone = { Text(text = "No job detail") },
        onLoading = { CircularLoadingIndicator() },
        onError = { errorResponse, _ ->
            Text(text = errorResponse.message)
        },
    ) { job ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Job name
            Text(
                text = job.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.padding(4.dp))

            // Job location
            Text(
                text = job.location,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.padding(8.dp))

            // Job Detail Views
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                views.forEach { view ->
                    JobDetailButton(label = view.label) {
                        onDetailSelected(view)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
private fun DetailView(state: JobDetailScreenState) {
    val jobDetailState = state.jobDetail
    val clientDetailState = state.clientDetail

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        jobDetailState.let { state ->
            when (state) {
                is UiState.Success -> JobDetailCard(state.data)
                // Handle other states (Loading, Error) as needed
                else -> {}
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        clientDetailState.let { state ->
            when (state) {
                is UiState.Success -> ClientDetailCard(state.data)
                // Handle other states (Loading, Error) as needed
                else -> {}
            }
        }
    }
}