package za.co.workshyelec.features.job

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.PaginatedResponse
import za.co.workshyelec.core.ktor.safeRequest
import za.co.workshyelec.features.job.models.Employee
import za.co.workshyelec.features.job.models.Job
import za.co.workshyelec.features.job.models.JobActivity
import za.co.workshyelec.features.job.models.JobType

class JobApiClient(
    private val httpClient: HttpClient
) {

    suspend fun getJobList(): ApiResponse<PaginatedResponse<Job>> = httpClient.safeRequest {
        url("/api/jobs")
        method = HttpMethod.Get
    }

    suspend fun getJobDetail(jobId: String): ApiResponse<Job> = httpClient.safeRequest {
        url("/api/jobs/$jobId")
        method = HttpMethod.Get
    }

    suspend fun getJobActivityList(jobId: String): ApiResponse<List<JobActivity>> =
        httpClient.safeRequest {
            url("/api/jobs/$jobId/activities")
            method = HttpMethod.Get
        }

    suspend fun getJobTypeDetail(jobTypeId: String): ApiResponse<JobType> = httpClient.safeRequest {
        url("/api/job-types/$jobTypeId")
        method = HttpMethod.Get
    }

    suspend fun getJobEmployeeList(jobId: String): ApiResponse<PaginatedResponse<Employee>> =
        httpClient.safeRequest {
            url("/api/jobs/$jobId/employees")
            method = HttpMethod.Get
        }
}