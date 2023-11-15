package com.anangkur.synrgychapter6.di.dagger

import android.app.Application
import android.content.Context
import com.anangkur.synrgychapter6.presentation.auth.login.LoginActivity
import com.anangkur.synrgychapter6.presentation.auth.register.RegisterActivity
import com.anangkur.synrgychapter6.presentation.blur.BlurActivity
import com.anangkur.synrgychapter6.presentation.home.HomeActivity
import com.anangkur.synrgychapter6.presentation.profile.ProfileActivity
import dagger.Component

@Component(
    modules = [
        GeneralModule::class,
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