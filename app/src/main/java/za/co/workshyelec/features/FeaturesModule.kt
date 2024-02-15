package za.co.workshyelec.features

import org.koin.dsl.module
import za.co.workshyelec.features.client.ClientModule
import za.co.workshyelec.features.home.HomeModule
import za.co.workshyelec.features.job.JobModule
import za.co.workshyelec.features.login.LoginModule
import za.co.workshyelec.features.profile.ProfileModule

val FeaturesModule = module {
    includes(
        HomeModule,
        LoginModule,
        JobModule,
        ClientModule,
        ProfileModule
    )
}