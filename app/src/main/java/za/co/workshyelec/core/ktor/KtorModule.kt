package za.co.workshyelec.core.ktor

import org.koin.dsl.module
import za.co.workshyelec.core.auth.AuthApiClient

val KtorModule = module {
    single {
        createHttpClient(get()) {
            val authApiClient: AuthApiClient = get()
            authApiClient.refreshAccessToken()
        }
    }
}