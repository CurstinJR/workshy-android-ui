package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.workshyelec.composables.CircularLoadingIndicator
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.composables.JobActivityCard
import za.co.workshyelec.features.job.models.JobActivity

@Composable
fun JobActivityListView(
    activityListState: UiState<List<JobActivity>>
) {
    activityListState.let { state ->
        when (state) {
            is UiState.Loading -> {
                CircularLoadingIndicator()
            }

            is UiState.Success -> {
                LazyColumn {
                    itemsIndexed(state.data) { _, activity ->
                        JobActivityCard(jobActivity = activity)
                    }
                }
            }

            is UiState.Error -> {
                Text(text = state.errorResponse.message, modifier = Modifier.padding(16.dp))
            }

            else -> {
                Text(text = "No job activities", modifier = Modifier.padding(16.dp))
            }
        }
    }
}