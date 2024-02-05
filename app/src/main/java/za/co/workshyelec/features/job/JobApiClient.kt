package za.co.workshyelec.features.job

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import za.co.workshyelec.core.api.ApiResponse
import za.co.workshyelec.core.common.PaginatedResponse
import za.co.workshyelec.core.ktor.safeRequest
import za.co.workshyelec.features.job.models.Job

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
}