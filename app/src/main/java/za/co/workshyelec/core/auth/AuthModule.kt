package za.co.workshyelec.core.auth

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AuthModule = module {
    single { UserSessionManager(androidContext()) }
    single { AuthApiClient(get(), get()) }
}