package com.anangkur.di.dagger.domain

import com.anangkur.domain.repository.AuthRepository
import com.anangkur.domain.usecase.AuthenticateUseCase
import com.anangkur.presentation.auth.login.CheckLoginUseCase
import com.anangkur.presentation.auth.login.SaveTokenUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideAuthenticateUseCase(authRepository: AuthRepository): AuthenticateUseCase {
        return com.anangkur.presentation.auth.login.AuthenticateUseCase(authRepository)
    }

    @Provides
    fun provideCheckLoginUseCase(authRepository: AuthRepository): CheckLoginUseCase {
        return CheckLoginUseCase(authRepository)
    }

    @Provides
    fun provideSaveTokenUseCase(authRepository: AuthRepository): SaveTokenUseCase {
        return SaveTokenUseCase(authRepository)
    }
}
