package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.composables.CircularLoadingIndicator
import za.co.workshyelec.composables.button.AppShapes
import za.co.workshyelec.composables.button.WSDynamicButton
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.navigation.NavigationHandlerImpl
import za.co.workshyelec.features.job.composables.ClientDetailCard
import za.co.workshyelec.features.job.composables.JobDetailBottomBar
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
    val screenState by jobDetailViewModel.screenState.collectAsState()

    BaseScreen(
        navController = navController,
        topBar = {
            JobDetailTopBar(
                jobId = jobDetailScreenArgs.jobId,
                onBackClick = { navigationHandler.goBack() }
            )
        },
        bottomBar = { JobDetailBottomBar() },
    ) {
        LazyColumn {
            item {
                JobDetailScreenContent(
                    state = screenState.jobDetail,
                    currentView = currentViewState,
                    onDetailSelected = { view ->
                        jobDetailViewModel.setCurrentView(view)
                    }
                )
            }

            item {
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
    }
}

@Composable
private fun JobDetailScreenContent(
    state: UiState<Job>,
    currentView: JobDetailView,
    onDetailSelected: (JobDetailView) -> Unit
) {
    val views = listOf(JobDetailView.Detail, JobDetailView.Activity)

    Column {
        when (state) {
            is UiState.Loading -> {
                CircularLoadingIndicator()
            }

            is UiState.Success -> {
                // Job name
                Text(
                    text = state.data.name,
                    style = MaterialTheme.typography.headlineMedium,
                )

                Spacer(modifier = Modifier.padding(4.dp))

                // Job location
                Text(
                    text = state.data.location,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                // Job Detail Views
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    views.forEach { view ->
                        val isSelected = view == currentView

                        WSDynamicButton(
                            text = view.label,
                            cornerRadius = AppShapes.largeCornerRadius,
                            isSelected = isSelected,
                            onClick = { onDetailSelected(view) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            // TODO: Implement custom Error UI composable
            is UiState.Error -> {
                Text(text = state.errorResponse.message)
            }

            // TODO: Implement custom None UI composable
            is UiState.None -> {
                Text(text = "No job detail")
            }

            else -> {}
        }
    }
}

@Composable
private fun DetailView(state: JobDetailScreenState) {
    val jobDetailState = state.jobDetail
    val clientDetailState = state.clientDetail

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        jobDetailState.let { state ->
            when (state) {
                is UiState.Success -> JobDetailCard(state.data)
                // Handle other states (Loading, Error) as needed
                else -> {}
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        clientDetailState.let { state ->
            when (state) {
                is UiState.Success -> ClientDetailCard(state.data)
                // Handle other states (Loading, Error) as needed
                else -> {}
            }
        }
    }
}