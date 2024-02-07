package za.co.workshyelec.features.job.jobDetail

sealed class JobDetailView {
    data object Detail : JobDetailView()
    data object Activity : JobDetailView()
    data object Workers : JobDetailView()
    data object Material : JobDetailView()
}

val JobDetailView.label: String
    get() = when (this) {
        JobDetailView.Detail -> "Detail"
        JobDetailView.Activity -> "Activity"
        JobDetailView.Workers -> "Workers"
        JobDetailView.Material -> "Material"
    }
