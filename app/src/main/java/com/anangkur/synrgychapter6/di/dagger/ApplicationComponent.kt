package com.anangkur.synrgychapter6.di.dagger

import android.app.Application
import com.anangkur.di.dagger.data.DataModule
import com.anangkur.di.dagger.domain.DomainModule
import com.anangkur.di.dagger.domain.UseCaseModule
import com.anangkur.di.dagger.presentation.PresentationModule
import com.anangkur.synrgychapter6.presentation.auth.login.LoginActivity
import com.anangkur.synrgychapter6.presentation.auth.register.RegisterActivity
import com.anangkur.synrgychapter6.presentation.blur.BlurActivity
import com.anangkur.synrgychapter6.presentation.home.HomeActivity
import com.anangkur.synrgychapter6.presentation.profile.ProfileActivity
import dagger.Component

@Component(
    modules = [
        PresentationModule::class,
        DomainModule::class,
        UseCaseModule::class,
        DataModule::class,
    ]
)
interface ApplicationComponent {

    val application: Application

    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: BlurActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: ProfileActivity)
}