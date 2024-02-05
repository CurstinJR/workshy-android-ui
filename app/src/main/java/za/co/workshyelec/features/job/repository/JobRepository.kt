package za.co.workshyelec.features.job.repository

import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.PaginatedResponse
import za.co.workshyelec.features.job.JobApiClient
import za.co.workshyelec.features.job.models.Job

class JobRepository(private val jobApiClient: JobApiClient) {
    suspend fun getJobList(): ApiResponse<PaginatedResponse<Job>> = jobApiClient.getJobList()

    suspend fun getJobDetail(jobId: String): ApiResponse<Job> = jobApiClient.getJobDetail(jobId)
}