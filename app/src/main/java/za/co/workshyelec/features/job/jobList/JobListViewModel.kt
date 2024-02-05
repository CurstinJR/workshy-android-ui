package za.co.workshyelec.features.job.jobList

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.BaseViewModel
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.navigation.NavigationEvent
import za.co.workshyelec.features.destinations.JobDetailScreenDestination
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.repository.JobRepository

class JobListViewModel(private val jobRepository: JobRepository) : BaseViewModel() {
    private val _jobList = MutableStateFlow<UiState<List<Job>>>(UiState.None)

    val jobList: StateFlow<UiState<List<Job>>> = _jobList.asStateFlow()

    init {
        getJobList()
    }

    private fun getJobList() {
        viewModelScope.launch {
            _jobList.value = UiState.Loading()
            when (val response = jobRepository.getJobList()) {
                is ApiResponse.Success -> _jobList.value = UiState.Success(response.data.items)
                is ApiResponse.Error -> _jobList.value = UiState.Error(response.error)
            }
        }
    }

    fun onJobClicked(jobId: String) {
        emitNavigationEvent(
            NavigationEvent.NavigateTo(JobDetailScreenDestination(jobId))
        )
    }
}