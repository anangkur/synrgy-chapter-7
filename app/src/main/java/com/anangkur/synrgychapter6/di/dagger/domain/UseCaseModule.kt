package com.anangkur.synrgychapter6.di.dagger.domain

import com.anangkur.synrgychapter6.domain.repository.AuthRepository
import com.anangkur.synrgychapter6.presentation.auth.login.usecase.AuthenticateUseCase
import com.anangkur.synrgychapter6.presentation.auth.login.usecase.CheckLoginUseCase
import com.anangkur.synrgychapter6.presentation.auth.login.usecase.SaveTokenUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideAuthenticateUseCase(
        authRepository: AuthRepository,
    ): AuthenticateUseCase {
        return AuthenticateUseCase(authRepository)
    }

    @Provides
    fun provideCheckLoginUseCase(
        authRepository: AuthRepository,
    ): CheckLoginUseCase {
        return CheckLoginUseCase(authRepository)
    }

    @Provides
    fun provideSaveTokenUseCase(
        authRepository: AuthRepository,
    ): SaveTokenUseCase {
        return SaveTokenUseCase(authRepository)
    }
}