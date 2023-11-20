package com.anangkur.synrgychapter6

import android.app.Application
import com.anangkur.synrgychapter6.di.dagger.DaggerApplicationComponent
import com.anangkur.synrgychapter6.di.dagger.presentation.PresentationModule

class Application : Application() {

//    lateinit var provider: Provider

    val appComponent = DaggerApplicationComponent.builder()
        .presentationModule(PresentationModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()

//        provider = Provider(this)

//        startKoin {
//            androidLogger()
//            androidContext(this@Application)
//            modules(appModules)
//        }
    }
}