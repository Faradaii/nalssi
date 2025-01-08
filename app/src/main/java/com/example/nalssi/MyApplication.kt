package com.example.nalssi

import android.app.Application
import com.example.nalssi.di.modules.DatabaseModule
import com.example.nalssi.di.modules.NetworkModule
import com.example.nalssi.di.modules.RepositoryModule
import com.example.nalssi.di.modules.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)

            modules(
                listOf(
                    NetworkModule,
                    DatabaseModule,
                    RepositoryModule,
                    useCaseModule,
                )
            )
        }
    }
}