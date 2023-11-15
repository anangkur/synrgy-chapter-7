package com.anangkur.synrgychapter6

import android.app.Application
import com.anangkur.synrgychapter6.di.koin.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class Application : Application() {

//    lateinit var provider: Provider

    override fun onCreate() {
        super.onCreate()

//        provider = Provider(this)

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModules)
        }
    }
}