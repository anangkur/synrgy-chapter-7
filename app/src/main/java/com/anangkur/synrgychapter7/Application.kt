package com.anangkur.synrgychapter7

import android.app.Application
import com.anangkur.di.dagger.presentation.PresentationModule
import com.anangkur.synrgychapter7.di.dagger.DaggerApplicationComponent

class Application : Application() {
    val appComponent =
        DaggerApplicationComponent.builder()
            .presentationModule(PresentationModule(this))
            .build()
}
