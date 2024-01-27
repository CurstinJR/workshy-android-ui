package za.co.workshyelec.features

import org.koin.dsl.module
import za.co.workshyelec.features.home.HomeModule
import za.co.workshyelec.features.job.JobModule
import za.co.workshyelec.features.login.LoginModule

val FeaturesModule = module {
    includes(
        HomeModule,
        JobModule,
        LoginModule
    )
}