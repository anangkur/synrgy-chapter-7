package com.anangkur.di.dagger.presentation

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

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

    @Provides
    fun provideDispatcher(): CoroutineContext {
        return Dispatchers.IO
    }
}
