package za.co.workshyelec.features.job.jobList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiErrorUtils
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.JobApiClient
import za.co.workshyelec.features.job.models.Job

sealed class NavigationEvent {
    data class NavigateToJobDetail(val jobId: String) : NavigationEvent()
}

class JobListViewModel(
    private val jobApiClient: JobApiClient
) : ViewModel() {
    private val _jobList = MutableStateFlow<UiState<List<Job>>>(UiState.None)
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()

    val jobList: StateFlow<UiState<List<Job>>> = _jobList.asStateFlow()
    val navigationEvent = _navigationEvent.asSharedFlow()

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
            _navigationEvent.emit(NavigationEvent.NavigateToJobDetail(jobId))
        }
    }
}