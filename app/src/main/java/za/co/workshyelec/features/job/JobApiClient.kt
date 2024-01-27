package za.co.workshyelec.features.job

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import za.co.workshyelec.core.common.PaginatedResponse
import za.co.workshyelec.features.job.models.Job

class JobApiClient(
    private val httpClient: HttpClient
) {
    suspend fun getJobList(): List<Job> {
        return httpClient.get("/api/jobs")
            .body<PaginatedResponse<Job>>()
            .items
    }

    suspend fun getJobDetail(jobId: String): Job {
        return httpClient.get("/api/jobs/$jobId")
            .body()
    }
}