package com.anangkur.synrgychapter7

import android.app.Application
import com.anangkur.synrgychapter7.di.dagger.DaggerApplicationComponent
import com.anangkur.di.dagger.presentation.PresentationModule

class Application : Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .presentationModule(PresentationModule(this))
        .build()
}