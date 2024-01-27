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
import za.co.workshyelec.core.common.handle
import za.co.workshyelec.core.navigation.NavigationHandler
import za.co.workshyelec.features.destinations.JobDetailScreenDestination
import za.co.workshyelec.features.job.composables.JobCard

@Composable
fun JobListScreen(
    navigationHandler: NavigationHandler,
    jobListViewModel: JobListViewModel = koinViewModel(),
) {
    // Observe the job list from the ViewModel
    val state by jobListViewModel.jobList.collectAsState()

    LaunchedEffect(jobListViewModel) {
        jobListViewModel.navigationEvent.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToJobDetail -> {
                    val direction = JobDetailScreenDestination(event.jobId)
                    navigationHandler.navigateTo(direction)
                }
            }
        }
    }

    state.handle(
        onSuccess = { jobList ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(jobList) { _, job ->
                    JobCard(job = job) { jobId ->
                        jobListViewModel.onJobClicked(jobId)
                    }
                }
            }
        },
        onError = { error, _ ->
            Text(text = error.message, modifier = Modifier.padding(16.dp))
        },
        onEmpty = {
            Text(text = "No jobs active", modifier = Modifier.padding(16.dp))
        }
    )
}