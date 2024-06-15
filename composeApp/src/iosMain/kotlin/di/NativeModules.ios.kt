package di

import com.example.database.Database
import com.example.database.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModules: Module = module {
    single { DriverFactory() }
    single { Database(get()) }
}