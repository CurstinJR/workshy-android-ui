package za.co.workshyelec.features.job.jobDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiErrorUtils
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.JobApiClient
import za.co.workshyelec.features.job.models.Job

class JobDetailViewModel(
    private val jobApiClient: JobApiClient,
    private val jobId: String
) : ViewModel() {
    private val _jobDetail = MutableStateFlow<UiState<Job>>(UiState.None)

    val jobDetail: StateFlow<UiState<Job>> = _jobDetail.asStateFlow()

    init {
        viewModelScope.launch {
            _jobDetail.value = UiState.Loading()
            try {
                val jobDetail = jobApiClient.getJobDetail(jobId)
                _jobDetail.value = UiState.Success(jobDetail)
            } catch (t: Throwable) {
                val apiError = ApiErrorUtils.parseErrorResponse(t)
                _jobDetail.value = UiState.Error(apiError, t as Exception?)
            }
        }
    }
}