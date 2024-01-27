package za.co.workshyelec

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import za.co.workshyelec.core.CoreModule
import za.co.workshyelec.features.FeaturesModule

class WorkshyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger(Level.DEBUG)
            androidLogger()
            androidContext(this@WorkshyApplication)
            modules(
                CoreModule,
                FeaturesModule
            )
        }
    }
}