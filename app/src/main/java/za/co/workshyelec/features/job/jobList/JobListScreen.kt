package za.co.workshyelec.features.job.jobList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import za.co.workshyelec.composables.WSCircularLoadingIndicator
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.navigation.NavigationHandlerImpl
import za.co.workshyelec.features.job.composables.JobCard

@Composable
fun JobListScreen(
    jobListViewModel: JobListViewModel = koinViewModel(),
    navigationHandlerImpl: NavigationHandlerImpl
) {
    // Observe the job list from the ViewModel
    val jobListState by jobListViewModel.jobList.collectAsState()

    LaunchedEffect(jobListViewModel) {
        jobListViewModel.navigationEvent.collect { event ->
            navigationHandlerImpl.handleNavigationEvent(event)
        }
    }

    when (val state = jobListState) {
        is UiState.Loading -> {
            WSCircularLoadingIndicator()
        }

        is UiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(state.data) { _, job ->
                    JobCard(job = job) {
                        jobListViewModel.onJobClicked(job.id)
                    }
                }
            }
        }

        is UiState.Error -> {
            Text(text = state.errorResponse.message, modifier = Modifier.padding(16.dp))
        }

        else -> {
            Text(text = "No jobs active", modifier = Modifier.padding(16.dp))
        }
    }
}