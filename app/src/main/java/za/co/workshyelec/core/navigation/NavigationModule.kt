package za.co.workshyelec.core.navigation

import androidx.navigation.NavController
import org.koin.dsl.module

val NavigationModule = module {
    factory<NavigationHandler> { (navController: NavController) ->
        NavigationHandlerImpl(navController)
    }
}