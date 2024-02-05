package za.co.workshyelec.features.job.jobDetail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.BaseViewModel
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.repository.JobRepository

class JobDetailViewModel(
    private val jobRepository: JobRepository,
    private val jobId: String
) : BaseViewModel() {
    private val _jobDetail = MutableStateFlow<UiState<Job>>(UiState.None)

    val jobDetail: StateFlow<UiState<Job>> = _jobDetail.asStateFlow()

    init {
        getJobDetail()
    }

    private fun getJobDetail() {
        viewModelScope.launch {
            _jobDetail.value = UiState.Loading()
            when (val response = jobRepository.getJobDetail(jobId)) {
                is ApiResponse.Success -> _jobDetail.value = UiState.Success(response.data)
                is ApiResponse.Error -> _jobDetail.value = UiState.Error(response.error)
            }
        }
    }
}