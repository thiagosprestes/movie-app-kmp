package di

import com.example.database.Database
import com.example.database.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModules: Module = module {
    single { DriverFactory(androidContext().applicationContext) }
    single { Database(get()) }
}