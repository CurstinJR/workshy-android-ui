package za.co.workshyelec.features.job.jobList

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiErrorUtils
import za.co.workshyelec.core.common.BaseViewModel
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.navigation.NavigationEvent
import za.co.workshyelec.features.destinations.JobDetailScreenDestination
import za.co.workshyelec.features.job.JobApiClient
import za.co.workshyelec.features.job.models.Job

class JobListViewModel(
    private val jobApiClient: JobApiClient
) : BaseViewModel() {
    private val _jobList = MutableStateFlow<UiState<List<Job>>>(UiState.None)

    val jobList: StateFlow<UiState<List<Job>>> = _jobList.asStateFlow()

    init {
        getJobList()
    }

    private fun getJobList() {
        viewModelScope.launch {
            _jobList.value = UiState.Loading()
            try {
                val jobList = jobApiClient.getJobList()
                _jobList.value =
                    if (jobList.isEmpty()) UiState.Empty else UiState.Success(jobList)
            } catch (t: Throwable) {
                val apiError = ApiErrorUtils.parseErrorResponse(t)
                _jobList.value = UiState.Error(apiError, t as Exception?)
            }
        }
    }

    fun onJobClicked(jobId: String) {
        viewModelScope.launch {
            emitNavigationEvent(
                NavigationEvent.NavigateTo(JobDetailScreenDestination(jobId))
            )
        }
    }
}