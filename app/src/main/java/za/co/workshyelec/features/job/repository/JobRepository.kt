package za.co.workshyelec.features.job.repository

import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.PaginatedResponse
import za.co.workshyelec.features.job.JobApiClient
import za.co.workshyelec.features.job.models.Employee
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.models.JobActivity
import za.co.workshyelec.features.job.models.JobType

class JobRepository(private val jobApiClient: JobApiClient) {
    suspend fun getJobList(): ApiResponse<PaginatedResponse<Job>> = jobApiClient.getJobList()

    suspend fun getJobDetail(jobId: String): ApiResponse<Job> = jobApiClient.getJobDetail(jobId)

    suspend fun getJobActivityList(jobId: String): ApiResponse<List<JobActivity>> =
        jobApiClient.getJobActivityList(jobId)

    suspend fun getJobTypeDetail(jobId: String): ApiResponse<JobType> =
        jobApiClient.getJobTypeDetail(jobId)

    suspend fun getJobEmployeeList(jobId: String): ApiResponse<PaginatedResponse<Employee>> =
        jobApiClient.getJobEmployeeList(jobId)
}