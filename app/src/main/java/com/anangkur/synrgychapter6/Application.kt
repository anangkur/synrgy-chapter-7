package com.anangkur.synrgychapter6

import android.app.Application
import com.anangkur.synrgychapter6.di.dagger.DaggerApplicationComponent
import com.anangkur.di.dagger.presentation.PresentationModule

class Application : Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .presentationModule(PresentationModule(this))
        .build()
}