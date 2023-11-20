package com.anangkur.synrgychapter6.di.dagger.presentation

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(
    private val application: Application,
) {

    @Provides
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }
}