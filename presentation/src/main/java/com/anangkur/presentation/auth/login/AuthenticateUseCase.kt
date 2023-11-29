package com.anangkur.presentation.auth.login

import com.anangkur.domain.repository.AuthRepository
import com.anangkur.domain.usecase.AuthenticateUseCase
import javax.inject.Inject

class AuthenticateUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : AuthenticateUseCase {
        override suspend operator fun invoke(
            username: String,
            password: String,
        ): String {
            return authRepository.authenticate(username, password)
        }
    }
