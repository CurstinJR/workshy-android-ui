package za.co.workshyelec.features.client

import org.koin.dsl.module
import za.co.workshyelec.features.client.repository.ClientRepository

val ClientModule = module {
    single { ClientApiClient(get()) }
    single { ClientRepository(get()) }
}