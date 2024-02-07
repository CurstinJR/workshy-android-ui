package za.co.workshyelec.features.job.jobDetail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.BaseViewModel
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.client.model.Client
import za.co.workshyelec.features.client.repository.ClientRepository
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.models.JobActivity
import za.co.workshyelec.features.job.repository.JobRepository

data class JobDetailScreenState(
    val jobDetail: UiState<Job> = UiState.None,
    val clientDetail: UiState<Client> = UiState.None,
    val activityList: UiState<List<JobActivity>> = UiState.None,
)

class JobDetailViewModel(
    private val jobId: String,
    private val jobRepository: JobRepository,
    private val clientRepository: ClientRepository,
) : BaseViewModel() {
    private val _currentView = MutableStateFlow<JobDetailView>(JobDetailView.Detail)
    private val _screenState = MutableStateFlow(JobDetailScreenState())

    val currentView: StateFlow<JobDetailView> = _currentView.asStateFlow()
    val screenState: StateFlow<JobDetailScreenState> = _screenState.asStateFlow()

    init {
        setCurrentView(JobDetailView.Detail)
    }

    fun setCurrentView(view: JobDetailView) {
        _currentView.value = view

        when (view) {
            JobDetailView.Detail -> {
                if (screenState.value.jobDetail is UiState.None) {
                    getJobDetail()
                }
            }

            JobDetailView.Activity -> {
                if (screenState.value.activityList is UiState.None) {
                    fetchActivityList()
                }
            }

            else -> {}
        }
    }

    private fun getJobDetail() {
        viewModelScope.launch {
            // Set job detail state to loading
            _screenState.value = _screenState.value.copy(jobDetail = UiState.Loading())

            when (val response = jobRepository.getJobDetail(jobId)) {
                is ApiResponse.Success -> {
                    // Update job detail and fetch client details
                    _screenState.value =
                        _screenState.value.copy(jobDetail = UiState.Success(response.data))
                    fetchClientDetails(response.data.client)
                }

                is ApiResponse.Error -> {
                    // Update error state for job details
                    _screenState.value =
                        _screenState.value.copy(jobDetail = UiState.Error(response.error))
                }
            }
        }
    }

    private fun fetchClientDetails(clientId: String) {
        viewModelScope.launch {
            // Update loading state for client details within _screenState
            _screenState.value = _screenState.value.copy(clientDetail = UiState.Loading())

            when (val response = clientRepository.getClientDetail(clientId)) {
                is ApiResponse.Success -> {
                    // Update success state with client details within _screenState
                    _screenState.value =
                        _screenState.value.copy(clientDetail = UiState.Success(response.data))
                }

                is ApiResponse.Error -> {
                    // Update error state within _screenState
                    _screenState.value =
                        _screenState.value.copy(clientDetail = UiState.Error(response.error))
                }
            }
        }
    }

    private fun fetchActivityList() {
        viewModelScope.launch {
            // Update loading state for activity list within _screenState
            _screenState.value = _screenState.value.copy(activityList = UiState.Loading())

            when (val response = jobRepository.getJobActivityList(jobId)) {
                is ApiResponse.Success -> {
                    // Update success state with activity list within _screenState
                    _screenState.value =
                        _screenState.value.copy(activityList = UiState.Success(response.data))
                }

                is ApiResponse.Error -> {
                    // Update error state within _screenState
                    _screenState.value =
                        _screenState.value.copy(activityList = UiState.Error(response.error))
                }
            }
        }
    }
}