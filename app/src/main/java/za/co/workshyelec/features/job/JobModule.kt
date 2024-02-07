package za.co.workshyelec.features.job

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import za.co.workshyelec.features.job.jobDetail.JobDetailViewModel
import za.co.workshyelec.features.job.jobList.JobListViewModel
import za.co.workshyelec.features.job.repository.JobRepository

val JobModule = module {
    single { JobApiClient(get()) }
    single { JobRepository(get()) }
    viewModel { JobListViewModel(get()) }
    viewModel { (jobId: String) ->
        JobDetailViewModel(
            jobId,
            get(),
            get()
        )
    }
}