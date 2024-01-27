package za.co.workshyelec.core

import org.koin.dsl.module
import za.co.workshyelec.core.auth.AuthModule
import za.co.workshyelec.core.ktor.KtorModule
import za.co.workshyelec.core.navigation.NavigationModule

val CoreModule = module {
    includes(
        KtorModule,
        AuthModule,
        NavigationModule
    )
}