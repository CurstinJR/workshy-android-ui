package za.co.workshyelec.core.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.dsl.module

val NavigationModule = module {
    factory<NavigationHandler> { (navigator: DestinationsNavigator) ->
        NavigationHandlerImpl(
            navigator
        )
    }
}